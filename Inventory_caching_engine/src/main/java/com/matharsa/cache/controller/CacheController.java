package com.matharsa.cache.controller;

import com.matharsa.cache.service.MemoryCacheService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final MemoryCacheService memoryCacheService;

    public CacheController(MemoryCacheService memoryCacheService) {
        this.memoryCacheService = memoryCacheService;
    }

    @GetMapping("/search")
    public Map<String, Object> queryInventory(@RequestParam String sku) {
        return memoryCacheService.lookupItem(sku);
    }
}
