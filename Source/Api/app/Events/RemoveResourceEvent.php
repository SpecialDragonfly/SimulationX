<?php
declare(strict_types=1);

namespace App\Events;

/**
 * Update state when Source provides resources
 */
class RemoveResourceEvent extends Event implements ResourceEvent
{
    /**
     * @var string
     */
    private $resourceRef;

    /**
     * @var int
     */
    private $quantity;

    /**
     * @param string $resourceRef
     * @param int $quantity
     */
    public function __construct($resourceRef, $quantity)
    {
        $this->resourceRef = $resourceRef;
        $this->quantity = $quantity;
    }

    /**
     * @return string
     */
    public function getResourceRef(): string
    {
        return $this->resourceRef;
    }

    /**
     * @return int
     */
    public function getQuantity(): int
    {
        return $this->quantity*-1;
    }
}