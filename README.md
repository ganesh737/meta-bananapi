# meta-bananapi

Un-Official OpenEmbedded layer for bananapi boards.

### Table of Contents
**[Info](#info)**<br>
**[Host Setup](#host-setup)**<br>
**[Downloading Dependencies](#downloading-dependencies)**<br>
**[How to Build](#how-to-build)**<br>
**[How to Update SD Card](#how-to-update-sd-card)**<br>
**[Device Setup](#device-setup)**<br>
**[Expected Output](#expected-output)**<br>
**[Helpful Links](#helpful-links)**<br>
**[System Features](#system-features)**<br>

## Info

This layer depends on the additional layer:
* meta-sunxi: https://github.com/xleng/meta-sunxi
* meta-openembedded: https://github.com/openembedded/meta-openembedded

This is tested with core-image-minimal based on yocto 2.6.1 on Ubuntu 16.04.6 LTS.
It may work on any other supported OS but this is not checked.

The image bpi-console-image starts up with systemd (requires local.conf)

## Host Setup

Check [Yocto Build Setup](https://www.yoctoproject.org/docs/2.6.1/ref-manual/ref-manual.html#required-packages-for-the-build-host)

## Downloading Dependencies

Create a folder (ex:*bpim1*) and clone the below repositories in it

#### Get meta-bananapi
```shell
git clone -b thud https://github.com/ganesh737/meta-bananapi.git
```

#### Get meta-sunxi
```shell
git clone -b thud https://github.com/ganesh737/meta-sunxi.git
```
Checked with commit id *b02cac*

#### Get meta-openembedded
```shell
git clone -b thud https://github.com/openembedded/meta-openembedded.git
```
Checked with commit id *4cd3a3*

#### Get poky
```shell
git clone -b thud https://git.yoctoproject.org/git/poky
```
Checked with commit id *faeb36*

#### Get meta-rauc
```shell
git clone -b thud https://github.com/rauc/meta-rauc.git
```
Checked with commit id *ec81f8*

## How to Build

#### Create a folder for the build -
```shell
mkdir build
```

The folder structure is expected to be as below -
```shell
bpim1/
├── build
├── meta-bananapi
├── meta-openembedded
├── meta-sunxi
└── poky
```

#### Setup the environment in the build folder -
```shell
source poky/oe-init-build-env build
cp ../meta-bananapi/conf/sample/bblayers.conf.sample conf/bblayers.conf
cp ../meta-bananapi/conf/sample/local.conf.sample conf/local.conf
```

Adapt the contents of **conf/bblayers.conf** to include the path of all the layers

Adapt the contents of **conf/layers.conf** for your *DL_DIR* and *SSTATE_DIR*

| WARNING: Make sure to adapt the paths in conf/bblayers.conf and conf/layers.conf for your machine paths else you may face weird build problems |
| --- |

#### Trigger Build and Image Generation
```shell
bitbake core-image-minimal
```

## How to Update SD Card

The resultant sd card image is available at
```shell
./tmp/deploy/images/bananapi/core-image-minimal-bananapi.sunxi-sdimg
```

You can flash it onto your SD Card as below -
```shell
sudo dd if=./tmp/deploy/images/bananapi/core-image-minimal-bananapi.sunxi-sdimg of=/dev/sdd
```

| WARNING: Make sure that /dev/sdd is your SD Card if not you can crash your system and delete your data |
| --- |

| Also checkout [BalenaEtcher](https://www.balena.io/etcher/) |
| --- |

## Device Setup

Check the below link for setup
[Prepare To Develop](http://wiki.banana-pi.org/Getting_Started_with_M1#Prepare_to_develop)

| INFO: This is important since the LED heartbeats are not yet available in this to inform that the device has booted |
| --- |

## Expected Output

Your device boots up with this image and you can observe the [systemd start-up logs](https://github.com/ganesh737/meta-bananapi/wiki/BPI-M1-startup-logs-(systemd))

## Helpful Links

[Changing init manager to systemd](https://yocto.yoctoproject.narkive.com/4FfIrwaI/how-to-use-systemd-as-system-init-manager)
[Preparing custom image](https://stackoverflow.com/questions/51002891/overwriting-yocto-classes-through-meta-layer)

## System Features