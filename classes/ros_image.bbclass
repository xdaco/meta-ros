# Metadata common to all ROS images.
#
# Copyright (c) 2019 LG Electronics, Inc.

# See https://github.com/agherzan/meta-raspberrypi/blob/master/docs/layer-contents.md -- but why aren't they always included by
# meta-raspberrypi?
IMAGE_INSTALL_append_rpi = " \
    ${MACHINE_EXTRA_RRECOMMENDS} \
"

# XXX (add description)
IMAGE_FEATURES[validitems] += "ros-implicit-workspace"
COMPLEMENTARY_GLOB[ros-implicit-workspace] = "*-implicitworkspace"
