<?php
declare(strict_types=1);

namespace App\Services;

use App\Events\RemoveResourceEvent;
use App\Model\Resource;
use Illuminate\Support\Facades\Event;

/**
 * Provide resources to the client and update the state using the State Manager node
 */
class ProvideService
{
    /**
     * @var StateService
     */
    private $stateService;

    /**
     * @param StateService $stateService
     */
    public function __construct(StateService $stateService)
    {
        $this->stateService = $stateService;
    }

    /**
     * @param Resource $resource
     * @param int $quantity
     *
     * @return bool
     * @throws \Exception
     */
    public function provide(Resource $resource, int $quantity)
    {
        $resourceState = $this->stateService->fetchState($resource);
        if ($quantity > $resourceState) {
            throw new \Exception();
        }

        Event::fire(new RemoveResourceEvent($resource->getName(),$quantity));
        return true;
    }
}