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
     * @var array
     */
    private $payload;

    /**
     * @param string $name
     * @param array $payload
     */
    public function __construct($name, array $payload)
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
     * @return array
     */
    public function getPayload(): array
    {
        return $this->payload;
    }
}