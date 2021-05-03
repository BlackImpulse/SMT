import axios from 'axios';
import authHeader from './auth.header';

const API_URL = 'http://localhost:8080/api/migration/';

class MigrationService {
    process(migration) {
        switch (migration.migrationTypeId) {
            case "2":
                return this.processPlaylists(migration);
            case "3":
                return this.processTracks(migration);
        }
    }

    processTracks(migration) {
        return axios.post(API_URL + 'process/tracks', {
            names: migration.names,
            fromService: {
                id: migration.fromServiceId
            },
            toService: {
                id: migration.toServiceId
            },
            username: migration.username,
            migrationType: {
                id: migration.migrationTypeId
            }
        }, {
            headers:  authHeader()
        }).then(response => {
            return response.data;
        })
    }
    processPlaylists(migration) {
        return axios.post(API_URL + 'process/playlists', {
            names: migration.names,
            fromService: {
                id: migration.fromServiceId
            },
            toService: {
                id: migration.toServiceId
            },
            username: migration.username,
            migrationType: {
                id: migration.migrationTypeId
            }
        }, {
            headers:  authHeader()
        }).then(response => {
            return response.data;
        })
    }
}

export default new MigrationService();
