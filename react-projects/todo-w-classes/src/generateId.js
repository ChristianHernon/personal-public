function* InfiniteGenerator() {
    let index = 0;
    while (true) {
        yield index++;
    }
}

export const GenerateID = InfiniteGenerator();