# Docker
## Heathcheck
### O que é?
É uma forma do Docker verificar se o serviço dentro do container está realmente funcionando, não apenas se o container está rodando.

### Analogia:
Imagine um restaurante:

- Container rodando = O restaurante está aberto (luz acesa)
- Healthcheck OK = O restaurante está atendendo clientes (cozinha funcionando)


Para verificar os resultados é só usar:
```
docker ps
```
Você verá algo como:
```
STATUS
Up 2 minutes (healthy)         # ✅ Tudo OK
Up 30 seconds (health: starting) # ⏳ Ainda testando
Up 5 minutes (unhealthy)       # ❌ Falhou nos testes
```

## Networks
### O que é?
Uma rede privada onde os containers podem conversar entre si, isolados da sua máquina e de outras redes.

### Analogia:
Imagine um condomínio fechado:

- Sua máquina = A rua pública
- Network auth-network = O condomínio
- Containers = Apartamentos dentro do condomínio 

Sem network explícita:
Docker cria uma network "default" automática, mas você não tem controle.

| Driver  | O que faz                                                                                                                                          |
|---------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| bridge  | Rede privada local (padrão) – containers conversam entre si                                                                                        |
| host    | Usa diretamente a rede da máquina host                                                                                                             |
| overlay | Rede distribuída usada em clusters (Docker Swarm/Kubernetes)                                                                                       |
| ipvlan  | Dá controle total sobre endereçamento IPv4/IPv6; permite integração com redes underlay, suporte a VLAN e roteamento L3                             |
| macvlan | Atribui um endereço MAC ao container, fazendo-o parecer um dispositivo físico na rede; útil para apps legadas que precisam de acesso direto à rede |
| none    | Desabilita completamente a rede do container; sem interfaces, apenas loopback                                                                      |

**Bridge** é o mais comum para desenvolvimento local.

## Volumes
### O que é?
Um lugar para guardar dados fora do container, para não serem perdidos quando o container for deletado.

### Analogia:
Imagine o container como um hotel:
- Sem volume: Você deixa as suas coisas no quarto. Quando faz checkout (deleta o container), perde tudo.
- Com volume: Você guarda as suas coisas num cofre do hotel. Mesmo fazendo checkout, suas coisas ficam lá.

### Onde ficam os dados?
No Linux/Mac:
```
/var/lib/docker/volumes/auth-service_postgres_data/_data
```

No Windows(WSL):
```
\\wsl$\docker-desktop-data\data\docker\volumes\auth-service_postgres_data\_data
```

### Driver: local
Significa que os dados ficam no disco local da sua máquina.
Outros drivers (avançado):

- nfs: Rede compartilhada
- s3: Amazon S3
- azure: Azure Storage

### Limpar os volumes:
```
docker-compose down -v  # Deleta containers E volumes
```



