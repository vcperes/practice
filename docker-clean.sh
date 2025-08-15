#!/bin/bash

# Cores
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m'

# Lista de imagens para manter (apenas o nome, sem tag)
KEEP_IMAGES="bitnami/kafka|redis|mongo"

echo -e "${BLUE}🔍 Parando todos os containers...${NC}"
docker ps -aq | xargs -r docker stop

echo -e "${YELLOW}🧹 Removendo apenas containers (mantendo volumes nomeados)...${NC}"
docker ps -aq | xargs -r docker rm -v --force

echo -e "${YELLOW}🧹 Removendo redes não usadas...${NC}"
docker network prune -f

echo -e "${CYAN}🗑️ Removendo imagens não desejadas (mantendo: ${KEEP_IMAGES})...${NC}"
docker images --format '{{.Repository}} {{.ID}}' \
  | grep -vE "^(${KEEP_IMAGES}) " \
  | awk '{print $2}' \
  | sort -u \
  | xargs -r docker rmi -f

echo -e "${YELLOW}🧽 Limpando recursos órfãos (sem remover volumes)...${NC}"
docker container prune -f
docker network prune -f
docker image prune -f

echo -e "${GREEN}✅ Limpeza concluída!${NC}"
read -p "Pressione ENTER para fechar..."