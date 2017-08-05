<?php
declare(strict_types=1);

namespace App\Services;

use App\Model\Resource;

/**
 * Fetch the state from the State Manager node
 */
class StateService
{
    public function __construct()
    {
    }

    public function fetchState(Resource $resource): int
    {
        return 100;
    }

    public function createStateResource($resource)
    {

    }
}