#!/bin/sh

# wait-for-it.sh
# Script que espera a que un servicio (host:port) esté disponible antes de ejecutar un comando.

set -e

HOST="$1"
PORT="$2"
shift 2
CMD="$@"

echo "Esperando a que $HOST:$PORT esté disponible..."

while ! nc -z "$HOST" "$PORT"; do
  sleep 1
done

echo "$HOST:$PORT está disponible. Ejecutando el comando: $CMD"
exec $CMD
