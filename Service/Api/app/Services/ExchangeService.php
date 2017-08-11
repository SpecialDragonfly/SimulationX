<?php
declare(strict_types=1);

namespace App\Services;

use App\Events\EmptyResourceEvent;
use App\Managers\StateManager;
use App\Model\Inventory;
use App\Model\Service;
use App\Model\StateResource;
use Illuminate\Support\Facades\Event;

/**
 * Provide resources to the client and update the state using the State Manager
 */
class ExchangeService
{
    /**
     * @var StateManager
     */
    private $stateManager;

    /**
     * @param StateManager $stateManager
     */
    public function __construct(StateManager $stateManager)
    {
        $this->stateManager = $stateManager;
    }

    /**
     * @param Service $service
     * @param int $quantity
     * @return StateResource
     * @throws \Exception
     */
    public function exchange(Service $service, int $quantity): StateResource
    {
        $inventory = $this->stateManager->getInventory($service);
        $resourceQuantity = $inventory->getSource()->getQuantity();

        $exchangeQuantity = $quantity*$service->getExchangeFactor();
        $resourceExchangedQuantity = $inventory->getExchange()->getQuantity();

        if ($exchangeQuantity > $resourceExchangedQuantity) {
            throw new \Exception("Not Enough destination", 400);
        } else if ($exchangeQuantity == $resourceExchangedQuantity) {
            Event::fire(new EmptyResourceEvent($inventory->getExchange()->getResource()->getName()));
        }

        $this->stateManager->saveInventory(
            new Inventory(
                new StateResource(
                    $service->getResource(),
                    $resourceQuantity + $quantity
                ),
                new StateResource(
                    $service->getResourceExchanged(),
                    $resourceExchangedQuantity - $exchangeQuantity
                )
            )
        );

        return new StateResource($service->getResourceExchanged(), $exchangeQuantity);
    }

    /**
     * @param Service $service
     * @return Inventory
     */
    public function getInventory(Service $service): Inventory
    {
        return $this->stateManager->getInventory($service);
    }
}