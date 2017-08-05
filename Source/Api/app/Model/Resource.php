<?php
declare(strict_types=1);

namespace App\Model;

/**
 * Represents the resource definition
 */
class Resource
{
    /**
     * @var string
     */
    private $name;

    /**
     * @var \stdClass
     */
    private $payload;

    /**
     * @param string $name
     * @param \stdClass $payload
     */
    public function __construct($name, \stdClass $payload)
    {
        $this->name = $name;
        $this->payload = $payload;
    }

    /**
     * @return string
     */
    public function getName(): string
    {
        return $this->name;
    }

    /**
     * @return \stdClass
     */
    public function getPayload(): \stdClass
    {
        return $this->payload;
    }
}