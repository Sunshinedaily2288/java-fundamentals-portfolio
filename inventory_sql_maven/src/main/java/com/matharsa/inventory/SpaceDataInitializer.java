package com.matharsa.inventory;

import com.matharsa.inventory.model.SpaceAsset;
import com.matharsa.inventory.repository.SpaceAssetRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Random;

@Component
public class SpaceDataInitializer implements CommandLineRunner {

    private final SpaceAssetRepository spaceAssetRepository;

    public SpaceDataInitializer(SpaceAssetRepository spaceAssetRepository) {
        this.spaceAssetRepository = spaceAssetRepository;
    }

    @Override
    public void run(String... args) {
        if (spaceAssetRepository.count() == 0) {
            Faker faker = new Faker();
            Random random = new Random();

            List<String> statuses = List.of("Ready for Launch", "Maintenance Required", "In Flight", "Decommissioned");
            List<String> depts = List.of("Propulsion", "Life Support", "Navigation", "Exochemistry", "Communications");

            System.out.println("🚀 Initializing deep-space telemetry asset tracking matrix...");

            for (int i = 0; i < 50; i++) {
                String itemName = faker.space().nasaSpaceCraft() + " " + faker.space().meteorite();
                String modelNum = "NX-" + random.nextInt(1000, 9999);
                String dept = depts.get(random.nextInt(depts.size()));
                String status = statuses.get(random.nextInt(statuses.size()));
                int qty = random.nextInt(1, 15);

                SpaceAsset asset = new SpaceAsset(itemName, modelNum, dept, status, qty);
                spaceAssetRepository.save(asset);
            }
            System.out.println("🪐 50 deep-space assets successfully synchronized with H2 registry!");
        }
    }
}
