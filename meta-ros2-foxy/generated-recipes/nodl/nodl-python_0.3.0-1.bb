# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_foxy
inherit ros_superflore_generated

DESCRIPTION = "Implementation of the NoDL API in Python."
AUTHOR = "Ubuntu Robotics <ubuntu-robotics@lists.launchpad.net>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "LGPL-2"
LIC_FILES_CHKSUM = "file://package.xml;beginline=8;endline=8;md5=b691248d2f70cdaeeaf13696ada5d47c"

ROS_CN = "nodl"
ROS_BPN = "nodl_python"

ROS_BUILD_DEPENDS = " \
    ament-index-python \
    python3-lxml \
"

ROS_BUILDTOOL_DEPENDS = ""

ROS_EXPORT_DEPENDS = " \
    ament-index-python \
    python3-lxml \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ament-index-python \
    python3-lxml \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_python3-pytest-mock} \
    ament-flake8 \
    ament-lint-auto \
    ament-lint-common \
    ament-mypy \
    python3-pytest \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros2-gbp/nodl-release/archive/release/foxy/nodl_python/0.3.0-1.tar.gz
ROS_BRANCH ?= "branch=release/foxy/nodl_python"
SRC_URI = "git://github.com/ros2-gbp/nodl-release;${ROS_BRANCH};protocol=https"
SRCREV = "20a2c4ed6df0b41c5660192a463e475254b35f8d"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_python"

inherit ros_${ROS_BUILD_TYPE}
