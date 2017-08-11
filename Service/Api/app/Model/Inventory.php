<?php
declare(strict_types=1);

namespace App\Model;

class Inventory
{
    /**
     * @var StateResource
     */
    private $source;

    /**
     * @var StateResource
     */
    private $exchange;

    /**
     * @param StateResource $source
     * @param StateResource $exchange
     */
    public function __construct(StateResource $source, StateResource $exchange)
    {
        $this->source = $source;
        $this->exchange = $exchange;
    }

    /**
     * @return StateResource
     */
    public function getSource(): StateResource
    {
        return $this->source;
    }

    /**
     * @param StateResource $source
     */
    public function setSource(StateResource $source)
    {
        $this->source = $source;
    }

    /**
     * @return StateResource
     */
    public function getExchange(): StateResource
    {
        return $this->exchange;
    }

    /**
     * @param StateResource $exchange
     */
    public function setExchange(StateResource $exchange)
    {
        $this->exchange = $exchange;
    }
}