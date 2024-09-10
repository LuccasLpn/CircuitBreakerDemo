# CircuitBreakerDemo

Este projeto demonstra o uso de `CircuitBreaker`, `RateLimiter`, e `Retry` com o Resilience4j em uma aplicação Spring Boot. Ele inclui endpoints configurados para lidar com falhas de API, limitando o número de requisições e tentando automaticamente em caso de falhas.

## Funcionalidades

- **CircuitBreaker**: Interrompe a comunicação com um serviço externo após um número de falhas.
- **RateLimiter**: Limita o número de requisições permitidas em um período de tempo.
- **Retry**: Tenta automaticamente a operação várias vezes em caso de falha.

## Requisitos

- JDK 17 ou superior
- Maven ou Gradle
- Spring Boot 3
- Resilience4j

## Como rodar o projeto localmente

1. Clone o repositório:

```bash
git clone https://github.com/LuccasLpn/CircuitBreakerDemo.git
cd circuitbreaker-demo

Certifique-se de ter o JDK instalado e configurado corretamente.

Para rodar o projeto, execute o seguinte comando:

Usando Maven:
./mvnw spring-boot:run

Usando Gradle:
./gradlew bootRun

O aplicativo será iniciado na porta padrão 8080. Você pode acessar o seguinte endpoint:

http://localhost:8080/countries

Testando o CircuitBreaker, RateLimiter, e Retry
Para testar o comportamento dessas funcionalidades, siga os passos abaixo:

1. Testando o CircuitBreaker

O CircuitBreaker é configurado para abrir após um número de falhas consecutivas. Para forçar o CircuitBreaker a abrir, você pode temporariamente alterar a URL do Feign Client para uma URL inválida ou interromper o serviço externo.

O CircuitBreaker mudará para o estado OPEN após atingir a taxa de falha configurada (50%). No estado OPEN, o fallback será acionado, retornando a mensagem "Country service unavailable".

O CircuitBreaker ficará em estado OPEN por 5 segundos antes de tentar novamente, entrando em HALF_OPEN. Se a próxima chamada for bem-sucedida, ele retornará ao estado CLOSED.

Logs do CircuitBreaker
Você pode observar a transição de estado do CircuitBreaker nos logs:

CircuitBreaker 'countriesCircuitBreaker' changed state from CLOSED to OPEN
CircuitBreaker 'countriesCircuitBreaker' changed state from OPEN to HALF_OPEN
CircuitBreaker 'countriesCircuitBreaker' changed state from HALF_OPEN to CLOSED


2. Testando o RateLimiter

O RateLimiter está configurado para permitir até 5 requisições por segundo. Para testar, faça múltiplas requisições rapidamente:

for i in {1..10}; do curl -X GET http://localhost:8080/countries; done

Se o limite for excedido, algumas requisições serão bloqueadas e o log indicará isso.

3. Testando o Retry

O Retry está configurado para tentar até 3 vezes, com um intervalo de 2 segundos entre as tentativas, em caso de falha.

Para testar o Retry, simule uma falha na API. Nos logs, você verá as tentativas de retry antes de acionar o fallback.
# CircuitBreakerDemo
