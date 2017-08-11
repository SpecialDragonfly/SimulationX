<?php
declare(strict_types=1);

namespace App\Console\Commands;

use Illuminate\Console\Command;
use Illuminate\Support\Facades\App;

class CreateService extends Command
{
    protected $name = 'create:service';

    protected $signature = "create:service {url} {source} {exchange}";

    /**
     * @var \App\Services\CreateService
     */
    private $createService;

    /**
     * @param \App\Services\CreateService $createService
     */
    public function __construct(\App\Services\CreateService $createService)
    {
        $this->createService = $createService;
        parent::__construct();
    }

    /**
     *
     */
    public function handle()
    {
        $service = App::make("service");
        $this->createService->createService(
            $this->argument("url"),
            $service,
            (int)$this->argument("source"),
            (int)$this->argument("exchange")
        );
        $this->info("Service created");
    }
}