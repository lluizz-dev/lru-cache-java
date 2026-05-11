# LRU Cache em Java

Esse projeto nasceu de uma curiosidade enquanto lia o livro **"Entendendo Algoritmos"** de Aditya Bhargava. O livro explica que quando você acessa uma página web pela primeira vez, ela é buscada no servidor — mas nas próximas vezes ela já está salva em cache, e abre muito mais rápido. Esse comportamento tem nome: **caching**. A implementação por baixo disso? Uma tabela hash.

Quis colocar esse conceito em prática com Java.

---

## O que é LRU Cache?

**LRU** significa *Least Recently Used* — ou seja, "o menos recentemente usado".

A ideia é simples: o cache tem uma capacidade máxima. Quando ele enche e um novo item precisa entrar, alguém tem que sair. Quem sai? O item que ficou mais tempo sem ser acessado.

É exatamente o que navegadores e servidores web fazem com páginas, imagens e recursos estáticos.

---

## Como funciona a implementação?

A estrutura central é o `LinkedHashMap` do Java, configurado com `accessOrder = true`. Isso faz com que, a cada acesso, o elemento seja movido para o final da lista interna — deixando o menos usado sempre no início, pronto para ser removido.

O método `removeEldestEntry` é chamado automaticamente após cada inserção. Quando o tamanho ultrapassa a capacidade definida, ele retorna `true` e o Java remove o elemento mais antigo sozinho.

```
[Page 01] → [Page 02]         cache com capacidade 2
↓ insere Page 03
[Page 02] → [Page 03]         Page 01 foi removida (menos usada)
```

---

## O que aprendi

- **Tabelas hash não guardam ordem** — o `HashMap` é rápido, mas não sabe qual elemento foi acessado há mais tempo. Precisei do `LinkedHashMap` para resolver isso.

- **Composição vs herança** — pensei em fazer o `LRUCache` estender o `LinkedHashMap` diretamente. Mas faz mais sentido dizer que um cache *tem* um mapa, não que ele *é* um mapa. Aprendi na prática por que composição costuma ser a escolha certa.

- **Generics** — criei a interface `Cache<K, V>` genérica para que o cache funcione com qualquer tipo, não só `String`. No começo parecia abstrato demais, mas fez total sentido ao implementar.

- **O custo do cache miss** — o `FakeDatabase` simula 2 segundos de latência. Testar na prática e ver o `[CACHE HIT]` retornar instantaneamente foi a melhor forma de entender por que cache importa.

---

## Estrutura do projeto

```
src/
└── main/
    └── java/
        ├── Cache.java          → interface genérica do cache
        ├── LRUCache.java       → implementação com LinkedHashMap
        ├── FakeDataBase.java   → simula um banco de dados lento
        └── Main.java           → menu interativo para testar
```

---

## Como rodar

1. Clone o repositório
2. Abra no IntelliJ ou qualquer IDE Java
3. Execute a classe `Main.java`
4. Teste buscando a mesma página duas vezes — veja a diferença entre `[CACHE MISS]` e `[CACHE HIT]`

> O cache está configurado com capacidade 2. Tente buscar três páginas diferentes e depois voltar para a primeira — o LRU em ação.

---

## Referências

- 📖 *Entendendo Algoritmos* — Aditya Bhargava
- 📄 [LinkedHashMap — Java 8 Docs](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html)