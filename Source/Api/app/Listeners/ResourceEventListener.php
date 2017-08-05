<?php
declare(strict_types=1);

namespace App\Listeners;

use App\Events\ResourceEvent;

/**
 * Update the State manager with the new resource amount
 */
class ResourceEventListener
{
    public function handle(ResourceEvent $resourceEvent)
    {
        //TODO notify State Manager about the state change
    }
}