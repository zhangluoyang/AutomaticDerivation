package utils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class L {

    public static <T> Stream<T> concatStream(Stream<? extends T> a, Stream<? extends T> b) {
        return Stream.concat(a, b);
    }

    public static <T> Stream<T> concatStream(Stream<? extends T> a, Stream<? extends T> b, Stream<? extends T>... more) {
        Stream<T> result = concatStream(a, b);
        for (Stream<? extends T> s : more) {
            result = Stream.concat(result, s);
        }
        return result;
    }

    /**
     * 单个家具转为 stream
     *
     * @param single 单独家具
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(T single) {
        if (single == null) {
            return Stream.empty();
        }
        return stream(L.asList(single));
    }

    /**
     * 集合转为 stream
     *
     * @param collection 集合
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(Collection<T> collection) {
        if (collection == null) {
            return Stream.empty();
        }
        return collection.stream();
    }

    /**
     * 迭代器转为 stream
     *
     * @param iterable 集合
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(Iterable<T> iterable) {
        if (iterable == null) {
            return Stream.empty();
        }
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * 数组转为 stream
     *
     * @param arrays 数组
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> stream(T[] arrays) {
        if (arrays == null) {
            return Stream.empty();
        }
        return Stream.of(arrays);
    }

    /**
     * 集合转为 并行 stream
     *
     * @param collection 集合
     * @param <T>
     * @return 并行stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> parallelStream(Collection<T> collection) {
        if (collection == null) {
            return Stream.empty();
        }
        return collection.parallelStream();
    }

    /**
     * 迭代器转为 并行 stream
     *
     * @param iterable 集合
     * @param <T>
     * @return stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> parallelStream(Iterable<T> iterable) {
        if (iterable == null) {
            return Stream.empty();
        }
        return StreamSupport.stream(iterable.spliterator(), true);
    }

    /**
     * 数组转为 并行 stream
     *
     * @param arrays 数组
     * @param <T>
     * @return 并行 stream
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> parallelStream(T[] arrays) {
        if (arrays == null) {
            return Stream.empty();
        }
        return Stream.of(arrays).parallel();
    }

    public static <T> void forEach(Iterable<T> iterable, Consumer<T> action) {
        if (iterable == null) {
            return;
        }
        for (T item : iterable) {
            if (action != null) {
                action.accept(item);
            }
        }
    }

    public static <T> void forEach(Iterable<T> iterable, BiConsumer<Integer, T> action) {
        if (iterable == null) {
            return;
        }
        int index = 0;
        for (T item : iterable) {
            if (action != null) {
                action.accept(index++, item);
            }
        }
    }

    /**
     * 判断列表是否为空或者null
     *
     * @param list
     * @return
     */
    public static final boolean isNullOrEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断迭代器是否为空
     *
     * @param iterable 迭代器
     * @return
     */
    public static final boolean isNullOrEmpty(Iterable<?> iterable) {
        if (iterable == null) {
            return true;
        }
        Iterator<?> iterator = iterable.iterator();
        return !iterator.hasNext();
    }


    /**
     * 判断列表是否不为空
     *
     * @param list
     * @return
     */
    public static final boolean isNotEmpty(Collection<?> list) {
        return list != null && !list.isEmpty();
    }

    /**
     * 判断迭代器是否不为空
     *
     * @param iterable 迭代器
     * @return
     */
    public static final boolean isNotEmpty(Iterable<?> iterable) {
        if (iterable != null) {
            Iterator<?> iterator = iterable.iterator();
            return iterator.hasNext();
        }
        return false;
    }

    /**
     * 多个实体转换为列表
     *
     * @param objs 多个实体参数
     * @param <T>
     * @return
     */
    public static final <T extends Object> List<T> asList(T... objs) {
        if (objs == null) {
            return emptyList();
        }
        List<T> result = new ArrayList<>();
        for (T obj : objs) {
            result.add(obj);
        }
        return result;
    }


    /**
     * 拼接列表，即
     * 多个集合合并为一个列表
     *
     * @param extLists 需要合并的多个集合参数
     * @return
     */
    public static <R, LIST extends Iterable<? extends R>> List<R> concatList(LIST... extLists) {
        if (extLists == null) {
            return emptyList();
        }
        List<R> result = new ArrayList<>();
        for (LIST extList : extLists) {
            if (isNullOrEmpty(extList)) {
                continue;
            }
            for (R o : extList) {
                result.add(o);
            }
        }
        return result;
    }


    /**
     * 两个子集合并为一个列表
     *
     * @param aList
     * @param bList
     * @param <T>
     * @param <a>
     * @param <b>
     * @param <ALIST>
     * @param <BLIST>
     * @return
     */
    public static <T extends Object, a extends T, b extends T,
            ALIST extends Collection<a>, BLIST extends Collection<b>>
    List<T> asList(ALIST aList, BLIST bList) {
        if (aList == null && bList == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        if (aList != null) {
            for (a list : aList) {
                result.add(list);
            }
        }
        if (bList != null) {
            for (b list : bList) {
                result.add(list);
            }
        }
        return result;
    }

    /**
     * 两个子集合并为一个set
     *
     * @param aList
     * @param bList
     * @param <T>
     * @param <a>
     * @param <b>
     * @param <ALIST>
     * @param <BLIST>
     * @return
     */
    public static <T extends Object, a extends T, b extends T,
            ALIST extends Collection<a>, BLIST extends Collection<b>>
    Set<T> asSet(ALIST aList, BLIST bList) {
        if (aList == null && bList == null) {
            return null;
        }
        Set<T> result = new HashSet<>();
        if (aList != null) {
            for (a list : aList) {
                result.add(list);
            }
        }
        if (bList != null) {
            for (b list : bList) {
                result.add(list);
            }
        }
        return result;
    }

    /**
     * 查找首条符合条件的记录 * @param predicate 条件 * @return
     */
    public static <T, R extends T> R find(Iterable<T> iterable, Predicate<T> predicate) {
        if (iterable == null) {
            return null;
        }
        for (T item : iterable) {
            if (predicate.test(item)) {
                return (R) item;
            }
        }
        return null;
    }

    /**
     * 查找符合条件的记录返回并删除 * @param predicate 条件 * @return
     */
    public static <T> Optional<T> findThenRemoveFrom(Iterable<T> iterable, Predicate<T> predicate) {
        if (iterable == null) {
            return Optional.empty();
        }

        Iterator<T> i = iterable.iterator();
        while (i.hasNext()) {
            T c = i.next();
            if (predicate.test(c)) {
                i.remove();
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    /**
     * 从集合中搜索到合适的数据后，从集合中移除数据，并且吧收集到的所有匹配的数据返回为stream
     *
     * @param iterable  要搜索的集合
     * @param predicate 搜索条件
     * @param <T>
     * @return 搜索到的数据的 stream
     */
    public static <T> Stream<T> findAllThenRemoveFrom(Iterable<T> iterable, Predicate<T> predicate) {
        if (iterable == null) {
            return Stream.empty();
        }

        Iterator<T> i = iterable.iterator();
        List<T> results = new ArrayList<>();
        while (i.hasNext()) {
            T c = i.next();
            if (predicate.test(c)) {
                i.remove();
                results.add(c);
            }
        }
        return stream(results);
    }

    /**
     * 按条件删除 * @param predicate
     */
    public static <T> void remove(Iterable<T> iterable, Predicate<T> predicate) {
        if (iterable == null) {
            return;
        }
        Iterator<T> i = iterable.iterator();
        while (i.hasNext()) {
            T c = i.next();
            if (predicate.test(c)) {
                i.remove();
            }
        }
    }


    /**
     * 查找所有符合条件的记录 * @param predicate * @return
     */
    public static <T> List<T> findAll(Iterable<T> iterable, Predicate<T> predicate) {
        if (iterable == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (T item : iterable) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 返回一个空列表
     *
     * @param <T> 任意类型
     * @return 空列表
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }
}
