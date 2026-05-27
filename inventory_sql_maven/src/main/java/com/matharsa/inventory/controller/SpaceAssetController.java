package com.matharsa.inventory.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.matharsa.inventory.model.SpaceAsset;
import com.matharsa.inventory.repository.SpaceAssetRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class SpaceAssetController {

    private final SpaceAssetRepository spaceAssetRepository;

    public SpaceAssetController(SpaceAssetRepository spaceAssetRepository) {
        this.spaceAssetRepository = spaceAssetRepository;
    }

    @GetMapping
    public List<SpaceAsset> getAllAssets() {
        // 💡 Changes the standard fetch to our custom alphabetical sort path
        return spaceAssetRepository.findAllByOrderByAssetNameAsc();
    }

    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        spaceAssetRepository.deleteById(id);
    }

    @PostMapping
    public SpaceAsset createAsset(@RequestBody SpaceAsset asset) {
        return spaceAssetRepository.save(asset);
    }
}
