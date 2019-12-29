#!/bin/sh

/usr/local/bin/envoy -c /etc/cluster-envoy.yaml --service-cluster envoy-proxy -l debug --restart-epoch 0
