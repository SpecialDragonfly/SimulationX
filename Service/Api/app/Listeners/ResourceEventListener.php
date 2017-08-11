<?php
declare(strict_types=1);

namespace App\Listeners;

use App\Client\EngineClient;
use App\Events\EmptyResourceEvent;

/**
 * Update the State manager with the new resource amount
 */
class ResourceEventListener
{
    /**
     * @var EngineClient
     */
    private $engineClient;

    /**
     * @param EngineClient $engineClient
     */
    public function __construct(EngineClient $engineClient)
    {
        $this->engineClient = $engineClient;
    }

    /**
     * @param EmptyResourceEvent $emptyResourceEvent
     */
    public function handle(EmptyResourceEvent $emptyResourceEvent)
    {
        $this->engineClient->notifyEmptyResource($emptyResourceEvent->getResourceRef());
    }
}