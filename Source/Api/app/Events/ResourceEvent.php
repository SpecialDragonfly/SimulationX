<?php
declare(strict_types=1);

namespace App\Events;

/**
 * Resource Event interface
 */
interface ResourceEvent
{
    /**
     * @return int
     */
    public function getQuantity(): int;

    /**
     * @return string
     */
    public function getResourceRef(): string;
}