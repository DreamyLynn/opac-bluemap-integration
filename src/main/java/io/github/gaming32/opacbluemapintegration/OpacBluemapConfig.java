package io.github.gaming32.opacbluemapintegration;

import org.quiltmc.qup.json.JsonReader;
import org.quiltmc.qup.json.JsonWriter;

import java.io.IOException;

public class OpacBluemapConfig {
    private int updateInterval = 12000; // Every 10 minutes
    private float markerMinY = 75f;
    private float markerMaxY = 75f;
    private boolean depthTest = false;

    public void read(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            final String key;
            switch (key = reader.nextName()) {
                case "updateInterval" -> updateInterval = reader.nextInt();
                case "markerMinY" -> markerMinY = reader.nextNumber().floatValue();
                case "markerMaxY" -> markerMaxY = reader.nextNumber().floatValue();
                case "depthTest" -> depthTest = reader.nextBoolean();
                default -> {
                    OpacBluemapIntegration.LOGGER.warn("Unknown OpenPaC BlueMap config key {}. Skipping.", key);
                    reader.skipValue();
                }
            }
        }
        reader.endObject();
    }

    public void write(JsonWriter writer) throws IOException {
        writer.beginObject();

        writer.comment("How often, in ticks, the markers should be refreshed. Set to 0 to disable automatic refreshing.");
        writer.comment("Default is 10 minutes (12000 ticks).");
        writer.name("updateInterval").value(updateInterval);

        writer.comment("The min and max Y for the markers. If these are the same, the marker will be drawn as a flat plane.");
        writer.comment("Default is 75 to 75.");
        writer.name("markerMinY").value(markerMinY);
        writer.name("markerMaxY").value(markerMaxY);

        writer.comment("If set to false, the markers won't be covered up by objects in front of it.");
        writer.comment("Default is false.");
        writer.name("depthTest").value(depthTest);

        writer.endObject();
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    public float getMarkerMinY() {
        return markerMinY;
    }

    public float getMarkerMaxY() {
        return markerMaxY;
    }

    public boolean isDepthTest() {
        return depthTest;
    }
}
