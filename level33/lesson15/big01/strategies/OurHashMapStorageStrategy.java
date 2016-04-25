package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by Влад on 08.04.2016.
 *
 * Стратегия не использует готовый HashMap, а сама является коллекцией.
 */
public class OurHashMapStorageStrategy implements StorageStrategy
{
    /**
     * Начальная емкость. Это число бакетов в хеш-таблице, где бакет - элемент хеш-таблицы, что содержит список данных в нее положенных.
     * Всегда является степенью 2. Это позволяет некоторые оптимизации.
     * Когда фактор загрузки превышен, то хеш-таблица автоматически увеличивает свою емкость вдвое.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * Фактор загрузки по умолчанию.
     * Определяет, насколько должна быть заполнена хеш-таблица перед автоматическим увеличением ее емкости.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Массив бакетов. Длина всегда равна степени двойки.
     * Увеличивается вдвое, если фактор загрузки превышен.
     */
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];

    /**
     * Количество мэппингов в хеш-таблице.
     */
    int size;

    /**
     * Пороговое значение, следующее значение размера количества мэппингов, по достижении которого размер хеш-таблицы (а точнее, размер массива бакетов) будет увеличен.
     * Рассчитывается как (емкость * фактор_загрузки).
     */
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);

    /**
     * Значение фактора загрузки для данной хеш-таблицы.
     */
    float loadFactor = DEFAULT_LOAD_FACTOR;

    /**
     * Хеширование.
     * Взято из реализации HashMap.
     */
    int hash(Long k) {
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    /**
     * Получает индекс в массиве бакетов.
     * Взято из реализации HashMap.
     */
    int indexFor(int hash, int length) {
        return hash & (length-1);
    }

    /**
     * Entry по ключу.
     * Скопировано.
     */
    Entry getEntry(Long key) {
        if (key == null)
            return null; //Если ключ null, то и Entry null.
        int hash = (key == null) ? 0 : hash(key); //Берем хеш-код ключа.
        for (Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next) { //Благодаря hash, определяем точно место в table.
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) //Если хеш-значение этого Entry совпадает с полученным, и его ключ и переданный ключ равны, то вернется этот entry.
                return e;
        }
        return null;
    }

    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == (1 << 30)) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, (1 << 30) + 1);
    }

    void transfer(Entry[] newTable) {
        int newCapacity = newTable.length;
        for (Entry e : table) {
            while(null != e) {
                Entry next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value)
    {
        if (value == null) {
            return false;
        }
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value)
    {
        addEntry(hash(key), key, value, indexFor(hash(key), table.length));
    }

    @Override
    public Long getKey(String value)
    {
        if (value == null) {
            return 0l;
        }
        for (Entry entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (value.equals(e.value)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    @Override
    public String getValue(Long key)
    {
        return null == getEntry(key) ? null : getEntry(key).getValue();
    }
}
