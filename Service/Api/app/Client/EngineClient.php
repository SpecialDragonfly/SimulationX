<?php
declare(strict_types=1);

namespace App\Client;

use App\Model\Service;
use GuzzleHttp\Client;

class EngineClient
{
    /**
     * @var Client
     */
    private $client;

    /**
     * @param Client $client
     */
    public function __construct(Client $client)
    {
        $this->client = $client;
    }

    /**
     * @param Service $service
     * @param string $url
     */
    public function registerType(Service $service, string $url)
    {
        $payload = [
            "action_url" => "{$url}/exchange",
            "status_url" => "{$url}/resources",
            "healthcheck_url" => "{$url}/ping",
            "resources" => [[
                "input" => $service->getResource()->getName(),
                "output" => $service->getResourceExchanged()->getName(),
                "exchange_rate" => $service->getExchangeFactor()
            ]]
        ];

        $this->client->post(env("ENGINE_URL")."/register", [
                "json" => $payload
        ]);
    }

    /**
     * @param string $resourceName
     */
    public function notifyEmptyResource(string $resourceName) {
        //
    }
}