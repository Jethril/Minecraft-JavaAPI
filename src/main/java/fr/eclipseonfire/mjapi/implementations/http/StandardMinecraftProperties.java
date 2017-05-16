/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.implementations.http;

import fr.eclipseonfire.mjapi.interfaces.MinecraftProperties;
import fr.eclipseonfire.mjapi.interfaces.MinecraftTextures;
import fr.eclipseonfire.mjapi.interfaces.Property;
import org.apache.commons.codec.binary.Base64;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StandardMinecraftProperties extends MinecraftProperties {

    public StandardMinecraftProperties() {
        super();
    }

    @Override
    public MinecraftTextures getTexturesProperty() throws IOException {
        Property property = this.getByName("textures");

        if (property == null) {
            return null;
        }

        String json = new String(Base64.decodeBase64(property.getValue()), StandardCharsets.UTF_8);

        return HttpAccountRepository.GSON.fromJson(json, HttpMinecraftTextures.class);
    }
}