SUMMARY = "A console development image"

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"

LICENSE = "MIT"

inherit image

# DEPENDS += ""

CORE_OS = " \
    kernel-modules \
    openssh openssh-keygen openssh-sftp-server \
    packagegroup-core-boot \
    tzdata \
"

DEV_SDK_INSTALL = ""

DEV_EXTRAS = ""

EXTRA_TOOLS_INSTALL = ""

BPI_STUFF = ""

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${DEV_SDK_INSTALL} \
    ${DEV_EXTRAS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${BPI_STUFF} \
"

IMAGE_INSTALL_append = " rauc"

set_local_timezone() {
    ln -sf /usr/share/zoneinfo/Asia/Kolkata ${IMAGE_ROOTFS}/etc/localtime
}

ROOTFS_POSTPROCESS_COMMAND += " \
    set_local_timezone ; \
"

LICENSE = "MIT"

IMAGE_ROOTFS_SIZE = "409600"

export IMAGE_BASENAME = "bpi-console-image"
