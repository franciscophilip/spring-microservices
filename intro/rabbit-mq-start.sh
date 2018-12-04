#!/usr/bin/env bash
docker run -d --hostname demo-rabbit -p 5672:5672 --name demo-rabbit rabbitmq:3