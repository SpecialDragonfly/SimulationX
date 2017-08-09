<?php
declare(strict_types=1);

namespace App\Http\Controllers;

use App\Services\ProvideService;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\App;

/**
 * Manage routing system
 */
class DefaultController extends Controller
{
    /**
     * @var ProvideService
     */
    private $provideService;

    /**
     * @param ProvideService $provideService
     */
    public function __construct(ProvideService $provideService)
    {
        $this->provideService = $provideService;
    }

    public function provideResource(string $quantity)
    {
        /** @var Resource $resource */
        $resource = App::make("resource");
        $isBool = $this->provideService->provide($resource, (int)$quantity);
        return $isBool ? (new Response([],200)) : (new Response([],500));
    }
}