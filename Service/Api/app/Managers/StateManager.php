<?php
declare(strict_types=1);

namespace App\Managers;

use App\Model\Inventory;
use App\Model\Service;
use App\Model\StateResource;

/**
 * Manage source state
 */
class StateManager
{
    /**
     * @param Service $service
     * @return Inventory
     */
    public function getInventory(Service $service): Inventory
    {
        $resourceJson = json_decode(file_get_contents(database_path("resources.json")),true);

        return new Inventory(
            new StateResource(
                $service->getResource(),
                $resourceJson[$service->getResource()->getName()]
            ),
            new StateResource(
                $service->getResourceExchanged(),
                $resourceJson[$service->getResourceExchanged()->getName()]
            )
        );
    }

    /**
     * @param Inventory $inventory
     */
    public function saveInventory(Inventory $inventory)
    {
        $resourceJson = [];
        $resourceJson[$inventory->getSource()->getResource()->getName()] = $inventory->getSource()->getQuantity();
        $resourceJson[$inventory->getExchange()->getResource()->getName()] = $inventory->getExchange()->getQuantity();

        file_put_contents(database_path("resources.json"), json_encode($resourceJson));
    }
}