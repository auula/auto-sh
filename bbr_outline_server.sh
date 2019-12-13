#!/bin/bash
echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33m开始安装Outline Server\033[0m"
curl -sS https://get.docker.com/ | sh
systemctl start docker
systemctl enable docker
systemctl status docker
wget -qO- https://raw.githubusercontent.com/Jigsaw-Code/outline-server/master/src/server_manager/install_scripts/install_server.sh | bash 
echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33mOutline Server已成功安装!\033[0m"
echo -e "\033[32m -----------------------------------------------------------------------------------------------\033[0m"
echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33m开始安装Google bbr\033[0m"
wget --no-check-certificate https://github.com/teddysun/across/raw/master/bbr.sh
chmod +x bbr.sh
./bbr.sh
echo -e "  [ \033[32mINFO\033[0m ] : \033[0;33mGoogle bbr已成功安装!\033[0m"