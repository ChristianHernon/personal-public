const FetchData = {
    HasCachedData: key => {
        JSON.parse(localStorage.getItem(key))
    },

    FetchMany: async urls => {
        const resolved = await Promise.all(urls.map(u => fetch(u)));
        const data = await Promise.all(resolved.map((r) => r.json()));
        return data;
    },

    FetchOne: async url => {
        return await (await fetch(url)).json()
    },
};

export const { FetchMany, FetchOne } = FetchData;
export default FetchData;