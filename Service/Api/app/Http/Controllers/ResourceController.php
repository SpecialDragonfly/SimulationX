<?php
declare(strict_types=1);

namespace App\Http\Controllers;

use App\Model\Service;
use App\Services\ExchangeService;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\App;

/**
 * Handle resource endpoints
 */
class ResourceController
{
    /**
     * @var ExchangeService
     */
    private $exchangeService;

    /**
     * @param ExchangeService $exchangeService
     */
    public function __construct(ExchangeService $exchangeService)
    {
        $this->exchangeService = $exchangeService;
    }

    public function exchangeResource(Request $request)
    {
        /** @var Service $service */
        $service = App::make("service");

        $quantity = (int)$request->json("input_amount");

        $stateResource = $this->exchangeService->exchange($service, $quantity);
        return new Response(json_encode(
            [
                "item" => $stateResource->getResource()->getPayload(),
                "quantity" => $stateResource->getQuantity()
            ]
        ),200);
    }

    /**
     * @return Response
     */
    public function getResources()
    {
        /** @var Service $service */
        $service = App::make("service");

        $inventory = $this->exchangeService->getInventory($service);

        return new Response(json_encode(
            [
                "input" => [
                    "name" => $inventory->getSource()->getResource()->getName(),
                    "volume" => $inventory->getSource()->getQuantity()
                ],
                "output" => [
                    "name" => $inventory->getExchange()->getResource()->getname(),
                    "quantity" => $inventory->getExchange()->getQuantity()
                ],
                "exchange_rate" => $service->getExchangeFactor()
            ]
        ),200);
    }

}