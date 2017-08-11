<?php
declare(strict_types=1);

namespace App\Services;

use App\Client\EngineClient;
use App\Managers\StateManager;
use App\Model\Inventory;
use App\Model\Service;
use App\Model\StateResource;

class CreateService
{
    /**
     * @var EngineClient
     */
    private $client;

    /**
     * @var StateManager
     */
    private $stateManager;

    /**
     * @param EngineClient $client
     * @param StateManager $stateManager
     */
    public function __construct(EngineClient $client, StateManager $stateManager)
    {
        $this->client = $client;
        $this->stateManager = $stateManager;
    }

    /**
     * @param string $url
     * @param Service $service
     * @param int $sourceQuantity
     * @param int $exchangeQuantity
     */
    public function createService(string $url, Service $service, int $sourceQuantity, int $exchangeQuantity)
    {
        $this->client->registerType($service, $url);
        $this->stateManager->saveInventory(
            new Inventory(
                new StateResource(
                    $service->getResource(),
                    $sourceQuantity
                ),
                new StateResource(
                    $service->getResourceExchanged(),
                    $sourceQuantity
                )
            )
        );
    }
}