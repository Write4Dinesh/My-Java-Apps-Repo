/**
 * Created by dinesh.k.masthaiah on 1/23/2018.
 */

public class Student {
    String mRollNumber;
    String mName;

    public Student() {

    }

    public Student(String name, String rollNumber) {
        mName = name;
        mRollNumber = rollNumber;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getRollNumber() {
        return mRollNumber;
    }

    public void setRollNumber(String mRollNumber) {
        this.mRollNumber = mRollNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (hashCode() == o.hashCode()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (mRollNumber != null && !mRollNumber.isEmpty()) {
            int hashCode = Integer.parseInt(mRollNumber);
            return hashCode;
        }
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Name=" + mName + ",Roll Number=" + mRollNumber;
    }
}
