<?php
declare(strict_types=1);

namespace App\Events;

/**
 * Update state when Resource are finished
 */
class EmptyResourceEvent extends Event
{
    /**
     * @var string
     */
    private $resourceRef;

    /**
     * @param $resourceRef
     */
    public function __construct($resourceRef)
    {
        $this->resourceRef = $resourceRef;
    }

    /**
     * @return string
     */
    public function getResourceRef(): string
    {
        return $this->resourceRef;
    }
}