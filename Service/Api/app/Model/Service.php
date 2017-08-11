<?php
declare(strict_types=1);

namespace App\Model;

/**
 * Represents a source with the resource is providing
 */
class Service
{
    /**
     * @var string
     */
    private $id;

    /**
     * @var Resource
     */
    private $resource;

    /**
     * @var Resource
     */
    private $resourceExchanged;

    /**
     * @var int
     */
    private $exchangeFactor;

    /**
     * @param $id
     * @param $resource
     * @param $resourceExchanged
     * @param int $exchangeFactor
     */
    public function __construct($id, $resource, $resourceExchanged, int $exchangeFactor)
    {
        $this->id = $id;
        $this->resource = $resource;
        $this->resourceExchanged = $resourceExchanged;
        $this->exchangeFactor = $exchangeFactor;
    }

    /**
     * @return string
     */
    public function getId(): string
    {
        return $this->id;
    }

    /**
     * @return Resource
     */
    public function getResource(): Resource
    {
        return $this->resource;
    }

    /**
     * @return Resource
     */
    public function getResourceExchanged(): Resource
    {
        return $this->resourceExchanged;
    }

    /**
     * @return int
     */
    public function getExchangeFactor(): int
    {
        return $this->exchangeFactor;
    }
}