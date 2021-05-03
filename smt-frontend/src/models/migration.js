export default class Migration {
    constructor(names, fromServiceId, toServiceId, username, migrationTypeId) {
        this.names = names;
        this.fromServiceId = fromServiceId;
        this.toServiceId = toServiceId;
        this.username = username;
        this.migrationTypeId = migrationTypeId;
    }
}