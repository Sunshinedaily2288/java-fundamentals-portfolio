package com.matharsa.inventory.repository;

import com.matharsa.inventory.model.SpaceAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpaceAssetRepository extends JpaRepository<SpaceAsset, Long> {
    // 💡 This magic naming convention tells Spring Data to automatically sort everything alphabetically!
    List<SpaceAsset> findAllByOrderByAssetNameAsc();
}
