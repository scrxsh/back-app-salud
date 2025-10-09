package com.unisangil.backend.appsalud.modules.geoModule;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class GeoService {

    private  final WebClient httpClient = WebClient.builder()
            .baseUrl("https://nominatim.openstreetmap.org")
            .build();

    public JSONObject buscar(String query) throws IOException {
        String respuesta = httpClient.get().
                uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("format", "json")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONArray jsonArray = new JSONArray(respuesta);

        if (!jsonArray.isEmpty()) {
            return jsonArray.getJSONObject(0);
        }

        return null;
    }
}
