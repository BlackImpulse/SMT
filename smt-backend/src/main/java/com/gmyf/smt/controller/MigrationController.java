package com.gmyf.smt.controller;

import com.gmyf.smt.factory.ServiceApiFactory;
import com.gmyf.smt.factory.api.ServiceApi;
import com.gmyf.smt.factory.model.Playlist;
import com.gmyf.smt.payload.MigrationPayload;
import com.gmyf.smt.service.api.MigrationService;
import com.gmyf.smt.service.api.TokenService;
import com.gmyf.smt.service.api.UserService;
import com.gmyf.smt.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/migration")
public class MigrationController {
    @Autowired
    private MigrationService migrationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/process/tracks")
    public ResponseEntity<?> processTrackMigration(@RequestBody MigrationPayload migrationPayload) {
        UserDto userDto = userService.getByUsername(migrationPayload.getUsername());
        ServiceApi toService = ServiceApiFactory.getServiceApi(migrationPayload.getToService().getId());
        String toServiceAccessToken = tokenService.getFreshToken(userDto.getId(),
                migrationPayload.getToService().getId()).getAccessToken();
        toService.migrateTracks(migrationPayload.getNames(), toServiceAccessToken);

        migrationService.saveMigration(migrationPayload, userDto.getId());

        return ResponseEntity.ok().body("Migration ended successfully");
    }

    @PostMapping("/process/playlists")
    public ResponseEntity<?> processPlaylistMigration(@RequestBody MigrationPayload migrationPayload) {
        UserDto userDto = userService.getByUsername(migrationPayload.getUsername());
        ServiceApi fromService = ServiceApiFactory.getServiceApi(migrationPayload.getFromService().getId());
        String fromServiceAccessToken = tokenService.getFreshToken(userDto.getId(),
                migrationPayload.getFromService().getId()).getAccessToken();
        String fromPlaylistId = fromService.getMyPlaylistByName(migrationPayload.getNames().get(0),
                fromServiceAccessToken);

        Playlist playlist = fromService.getPlaylist(fromPlaylistId, fromServiceAccessToken);
        ServiceApi toService =  ServiceApiFactory.getServiceApi(migrationPayload.getToService().getId());
        String toServiceAccessToken = tokenService.getFreshToken(userDto.getId(),
                migrationPayload.getToService().getId()).getAccessToken();
        toService.migratePlaylists(Collections.singletonList(playlist), toServiceAccessToken);

        migrationService.saveMigration(migrationPayload, userDto.getId());

        return ResponseEntity.ok().build();
    }
}
