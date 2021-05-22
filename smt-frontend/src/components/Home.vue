<template>
  <div class="home">
    <div class="main-screen">
      <p>TRANSFER YOUR PLAYLIST AND FAVORITES</p>
      <img src="../assets/pics/song_wave.png" alt="SongWave" class="song-wave"/>
    </div>
    <div class="migration-screen">
      <div class="from-container">
        <div id="from" class="services">
          <img id="1_from" src="../assets/pics/spotify.png" alt="Spotify" @click="fromServiceClick"/>
          <img id="2_from" src="../assets/pics/youtube.png" alt="Youtube" @click="fromServiceClick"/>
          <img id="3_from" src="../assets/pics/boom.png" alt="Boom" title="Coming soon"/>
          <img id="4_from" src="../assets/pics/yandex.png" alt="Yandex" title="Coming soon"/>
          <img id="5_from" src="../assets/pics/apple.png" alt="Apple" title="Coming soon"/>
        </div>
        <div class="items-checkboxes">
          <label> Albums
            <input id="albums" type="checkbox" v-model="albumsChecked" @input="onItemsCheck"/>
          </label>
          <label> Playlists
            <input id="playlists" type="checkbox" v-model="playlistsChecked" @input="onItemsCheck"/>
          </label>
          <label> Tracks
            <input id="tracks" type="checkbox" v-model="tracksChecked" @input="onItemsCheck"/>
          </label>
        </div>
        <div class="items-container">
          <div class="search-icon-container">
            <img src="../assets/pics/loupe.svg" class="search-icon" alt="Loupe"/>
          </div>
          <input type="text" class="search" @input="filterItems"/>
          <ul>
            <li v-for="item in activeItemsList" :key="item.message">
              <div class="item-card">
                <img v-bind:src="item.imageSrc"/>
                <p class="name">{{ item.name }}</p>
                <p class="author">{{ item.authorName }}</p>
                <input type="checkbox" v-model="item.checked"/>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="migration-button">
        <img class="spinner" v-bind:class="{rotating: isProcessing}" src="../assets/pics/recycle.svg" alt="Recycle"
             @click="spinnerClick"/>
      </div>
      <div class="to-container">
        <div class="services">
          <img id="1_to" src="../assets/pics/spotify.png" alt="Spotify" @click="toServiceClick"/>
          <img id="2_to" src="../assets/pics/youtube.png" alt="Youtube" @click="toServiceClick"/>
          <img id="3_to" src="../assets/pics/boom.png" alt="Boom" title="Coming soon"/>
          <img id="4_to" src="../assets/pics/yandex.png" alt="Yandex" title="Coming soon"/>
          <img id="5_to" src="../assets/pics/apple.png" alt="Apple" title="Coming soon"/>
        </div>
        <div class="message-container">
          <p>{{message}}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TokenService from '@/service/token.service';
import ServiceService from '@/service/service.service';
import Migration from '@/models/migration';
import MigrationService from '@/service/migration.service';

export default {
  name: 'Home',
  data() {
    return {
      migrationId: null,
      fromService: null,
      serviceFromPicked: false,
      toService: null,
      serviceToPicked: false,
      albumsChecked: false,
      tracksChecked: false,
      playlistsChecked: false,
      isProcessing: false,
      itemsList: [],
      activeItemsList: [],
      message: ""
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  methods: {
    onItemsCheck($event) {
      if (this.serviceFromPicked && this.fromService) {
        switch ($event.target.id) {
          case "albums":
            this.tracksChecked = false;
            this.playlistsChecked = false;
            this.migrationId = 1;
            break;
          case "playlists":
            this.albumsChecked = false;
            this.tracksChecked = false;
            this.migrationId = 2;
            break;
          case "tracks":
            this.albumsChecked = false;
            this.playlistsChecked = false;
            this.migrationId = 3;
            break;
        }

        if (!$event.target.checked) {
          this.itemsList = [];
          this.activeItemsList = [];
          return;
        }

        TokenService.getToken(1, this.fromService).then(async (response) => {
          if (response.data.token) {
            let methodName = `get${$event.target.id.charAt(0).toUpperCase() + $event.target.id.slice(1)}`
            this.itemsList = await ServiceService[methodName](response.data.token, this.fromService);
            this.activeItemsList = this.itemsList;
          } else {
            window.open(response.data.url);
          }
        })
      } else {
        $event.target.checked = false;
      }
    },
    spinnerClick() {
      if (!this.isProcessing && this.serviceFromPicked && this.serviceFromPicked && this.itemsList.length !== 0) {
        let migration = new Migration(
            this.itemsList.filter(item => item.checked).map(item => this.migrationId === 2 ? item.name :
                item.authorName + " " + item.name),
            this.fromService,
            this.toService,
            this.$store.state.auth.user.username,
            this.migrationId
        )
        this.isProcessing = !this.isProcessing;
        MigrationService.process(migration).then(async (response) => {
          this.message = response.data;
          this.isProcessing = !this.isProcessing;
        });
      }
    },
    fromServiceClick($event) {
      if (!this.loggedIn || this.serviceFromPicked && this.fromService !== $event.target.id[0]) {
        return;
      }
      this.serviceFromPicked = !this.serviceFromPicked;
      if (this.serviceFromPicked) {
        $event.target.classList.toggle('chosen');
        this.fromService = $event.target.id[0];
      } else {
        $event.target.classList = [];
        this.albumsChecked = false;
        this.tracksChecked = false;
        this.playlistsChecked = false;
        this.itemsList = [];
        this.activeItemsList = [];
        this.fromService = null;
      }
    },
    toServiceClick($event) {
      if (!this.loggedIn || !this.serviceFromPicked && this.serviceToPicked && this.toService !== $event.target.id[0]) {
        return;
      }

      this.serviceToPicked = !this.serviceToPicked;
      if (this.serviceToPicked) {
        $event.target.classList.toggle('chosen');
        this.toService = $event.target.id[0];
      } else {
        $event.target.classList = [];
        this.toService = null;
      }

      TokenService.getToken(1, this.toService).then(async (response) => {
        if (!response.data.token) {
          $event.target.classList = [];
          this.serviceToPicked = false;
          this.toService = null;
          window.open(response.data.url);
        }
      })
    },
    filterItems($event) {
      if ($event.target.value && $event.target.value !== "" && this.itemsList.length !== 0) {
        let regexp = new RegExp($event.target.value + "*");
        this.activeItemsList = this.itemsList.filter(item => regexp.test(item.name.toLowerCase()));
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.home .main-screen {
  margin-left: 339px;
  margin-top: 200px;
  background: transparent;
}

.home .main-screen p {
  width: 491px;
  overflow-wrap: break-word;
  font-family: Bungee Outline, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 80px;
  line-height: 96px;
  text-align: center;
  margin-bottom: 0;

  color: #000000;
}

.home .main-screen img.song-wave {
  margin-top: 24px;
  margin-right: 350px;
}

.home .migration-screen {
  margin-top: 99px;
  margin-bottom: 87px;
  text-align: left;
}

.home .migration-screen .services {
  margin-top: 207px;
  margin-bottom: 39px;
  text-align: left;
}

.home .migration-screen .items-checkboxes {
  text-align: left;
  margin-bottom: 44px;
}

.home .migration-screen .items-checkboxes label {
  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: bold;
  font-size: 28px;
  line-height: 46px;

  color: #657EFF;
}

.home .migration-screen .services img {
  width: 65px;
  height: 65px;
  margin-right: 15px;
}

.home .migration-screen .from-container {
  display: inline-block;
}

.home .migration-screen .to-container {
  display: inline-block;
  position: relative;
  margin-left: 120px;
}

.home .migration-screen .items-container {
  width: 402px;
  height: 506px;

  background: #C4C4C4;
  border-radius: 15px;

  overflow-y: auto;
}

.home .migration-screen .items-container ul {
  list-style: none;
  margin-top: 28px;
  padding-left: 29px;
}

.home .migration-screen .search-icon-container {
  width: 36px;
  height: 43px;
  background: #657EFF;
  border-radius: 15px 0 0 15px;
  border-color: transparent;
  display: inline-block;
  margin-left: 43px;
}

.home .migration-screen .items-container img.search-icon {
  width: 18px;
  height: 18px;
  margin-top: 10px;
  margin-left: 10px;
}

.home .migration-screen .items-container input.search {
  margin-top: 28px;
  width: 266px;
  height: 37px;
  display: inline-block;

  background: #657EFF;
  border-radius: 0 15px 15px 0;
  border-color: transparent;

  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 24px;
  line-height: 35px;

  color: rgba(0, 0, 0, 0.48);
}

.home .migration-screen .items-container .item-card {
  margin-top: 15px;
  width: 342px;
  height: 59px;

  background: #636363;
  border-radius: 15px;

  position: relative;
  text-align: left;
}

.home .migration-screen .items-container .item-card img {
  width: 46px;
  height: 46px;
  margin-left: 7px;
  margin-top: 7px;
  display: inline-block;
}

.home .migration-screen .items-container .item-card p.author {
  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: normal;
  font-size: 12px;
  line-height: 17px;
  display: inline-block;
  margin-left: 18px;
  margin-top: 33px;
  position: absolute;
  overflow-x: hidden;

  color: #000000
}

.home .migration-screen .items-container .item-card p.name {
  width: 220px;
  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: bold;
  font-size: 14px;
  line-height: 23px;
  display: inline-block;
  margin-bottom: 26px;
  margin-left: 17px;
  position: absolute;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  color: #000000;
}

.home .migration-screen .items-container .item-card input {
  position: absolute;
  margin-top: 23px;
  margin-left: 253px;
  display: inline-block;
}

.home .migration-screen .migration-button {
  display: inline-block;
  margin-left: 111px;

}

.home .migration-screen .migration-button img.spinner {
  width: 133px;
  height: 144px;
  margin-bottom: 245px;
}

.rotating {
  -webkit-animation: rotating 1s linear infinite;
  -moz-animation: rotating 1s linear infinite;
  -ms-animation: rotating 1s linear infinite;
  -o-animation: rotating 1s linear infinite;
  animation: rotating 1s linear infinite;
}

@keyframes rotating {
  from {
    transform: rotate(0deg);
    -o-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -moz-transform: rotate(0deg);
    -webkit-transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
    -o-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -webkit-transform: rotate(360deg);
  }
}

@-webkit-keyframes rotating {
  from {
    transform: rotate(0deg);
    -webkit-transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
    -webkit-transform: rotate(360deg);
  }
}

.home .migration-screen .to-container .message-container {
  width: 402px;
  height: 581px;

  display: inline-block;
  background: #C4C4C4;
  border-radius: 15px;
  vertical-align: bottom;
}

.home .migration-screen .to-container .message-container p {
  text-align: center;
  font-family: Karma, sans-serif;
  font-style: normal;
  font-weight: bold;
  font-size: 14px;
  line-height: 23px;
  color: #000000;
  margin: 100px;
}

.home .migration-screen .services img.chosen {
  border-radius: 15px;
  box-shadow: 0 0 25px #FFFFFF;
}
</style>
