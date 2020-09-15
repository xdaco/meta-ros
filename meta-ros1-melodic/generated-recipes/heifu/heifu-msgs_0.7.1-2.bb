# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Common message definitions for heifu"
AUTHOR = "Fábio Azevedo <fabio.azevedo@beyond-vision.pt>"
ROS_AUTHOR = "Fábio Azevedo <fabio.azevedo@beyond-vision.pt>"
HOMEPAGE = "https://gitlab.pdmfc.com/drones/heifu"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "heifu"
ROS_BPN = "heifu_msgs"

ROS_BUILD_DEPENDS = " \
    geometry-msgs \
    message-generation \
    std-msgs \
    std-srvs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    geometry-msgs \
    message-runtime \
    std-msgs \
    std-srvs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    geometry-msgs \
    message-runtime \
    std-msgs \
    std-srvs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/amferreiraBEV/heifu-release/archive/release/melodic/heifu_msgs/0.7.1-2.tar.gz
ROS_BRANCH ?= "branch=release/melodic/heifu_msgs"
SRC_URI = "git://github.com/amferreiraBEV/heifu-release;${ROS_BRANCH};protocol=https"
SRCREV = "d17df7b7ba392da1f5eaf94674a1c0d569b476f1"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
