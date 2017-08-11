<?php
declare(strict_types=1);

namespace App\Model;

class StateResource
{
    /**
     * @var Resource
     */
    private $resource;

    /**
     * @var int
     */
    private $quantity;

    /**
     * @param Resource $resource
     * @param int $quantity
     */
    public function __construct(Resource $resource, int $quantity)
    {
        $this->resource = $resource;
        $this->quantity = $quantity;
    }

    /**
     * @return int
     */
    public function getQuantity(): int
    {
        return $this->quantity;
    }

    /**
     * @return Resource
     */
    public function getResource(): Resource
    {
        return $this->resource;
    }

    /**
     * @param int $quantity
     */
    public function setQuantity(int $quantity)
    {
        $this->quantity = $quantity;
    }
}