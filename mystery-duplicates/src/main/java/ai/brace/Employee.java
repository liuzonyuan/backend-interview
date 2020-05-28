package ai.brace;

import java.util.Objects;

/**
 * original class use the default hashcode and equals methods and each time the object will be different,
 * and that's why the output can't get the count correctly.
 */
public class Employee
{
    public String firstName;
    public String middleInitial;
    public String lastName;
    public String socialSecurityNumber;

    public Employee(String _lastName, String _firstName, String _middleInitial, String _socialSecurityNumber)
    {
        firstName = _firstName;
        middleInitial = _middleInitial;
        lastName = _lastName;
        socialSecurityNumber = _socialSecurityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(middleInitial, employee.middleInitial) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(socialSecurityNumber, employee.socialSecurityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleInitial, lastName, socialSecurityNumber);
    }
}
