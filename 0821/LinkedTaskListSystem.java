class Task {
    private final String id;
    private final String title;

    Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + title;
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    TaskNode(Task task) {
        this.task = task;
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    boolean addFirst(Task task) {
        if (findById(task.getId()) != null) {
            return false;
        }
        TaskNode node = new TaskNode(task);
        node.next = head;
        head = node;
        size++;
        return true;
    }

    boolean addLast(Task task) {
        if (findById(task.getId()) != null) {
            return false;
        }
        TaskNode node = new TaskNode(task);
        if (head == null) {
            head = node;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
        return true;
    }

    Task findById(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.getId().equals(id)) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    boolean removeById(String id) {
        if (head == null) {
            return false;
        }
        if (head.task.getId().equals(id)) {
            head = head.next;
            size--;
            return true;
        }
        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.getId().equals(id)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    boolean insertAfter(String existingId, Task task) {
        if (findById(task.getId()) != null) {
            return false;
        }
        TaskNode current = head;
        while (current != null) {
            if (current.task.getId().equals(existingId)) {
                TaskNode node = new TaskNode(task);
                node.next = current.next;
                current.next = node;
                size++;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    int size() {
        return size;
    }

    void printAll() {
        StringBuilder result = new StringBuilder("[");
        TaskNode current = head;
        while (current != null) {
            result.append(current.task);
            current = current.next;
            if (current != null) {
                result.append(", ");
            }
        }
        result.append("]");
        System.out.println(result);
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        System.out.println("空 list 刪除 T001：" + list.removeById("T001"));
        list.printAll();

        list.addLast(new Task("T001", "Backup"));
        list.addLast(new Task("T002", "Update"));
        list.addLast(new Task("T003", "Report"));
        list.printAll();

        System.out.println("重複新增 T002：" + list.addFirst(new Task("T002", "Duplicate")));

        System.out.println("insertAfter T002：" + list.insertAfter("T002", new Task("T004", "Review")));
        list.printAll();

        System.out.println("刪除 head T001：" + list.removeById("T001"));
        list.printAll();

        System.out.println("刪除 middle T004：" + list.removeById("T004"));
        list.printAll();

        System.out.println("刪除 tail T003：" + list.removeById("T003"));
        list.printAll();

        System.out.println("刪除不存在 T999：" + list.removeById("T999"));
        System.out.println("size=" + list.size());
    }
}
