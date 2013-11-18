package samples.with.abstractmethods;

public abstract class Entity<K> {

    public abstract K getKey();
    public abstract void setKey(K key);
   
    @Override
    public final int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Entity<K> other = (Entity<K>)obj;
        if (getKey() == null) {
            if (other.getKey() != null) {
                return false;
            }
        } else if (!getKey().equals(other.getKey())) {
            return false;
        }
        return true;
    }

    @Override
    public abstract String toString();
}
