# Act 4 – Árboles Binarios (BST) y Gestión de Empleados

Este proyecto implementa un Árbol Binario de Búsqueda (BST) en Java con operaciones básicas (inserción, eliminación, búsqueda) y recorridos (preorden, inorden, postorden). Además, incluye un caso práctico de gestión de empleados utilizando el BST para optimizar búsquedas por ID.

## Objetivos

- Comprender la estructura y funcionamiento de los árboles binarios.
- Implementar y manipular árboles binarios en Java.
- Aplicar los conceptos en un caso práctico: gestión de empleados.

## Estructura del código

- `src/act4ed/tree/Node.java`: Nodo genérico del árbol.
- `src/act4ed/tree/ArbolBinario.java`: Implementación del BST con insert, remove, contains y recorridos.
- `src/act4ed/employee/Employee.java`: POJO de empleado (id, nombre, departamento) y Comparable por ID.
- `src/act4ed/employee/EmployeeDirectory.java`: Directorio que usa BST para almacenar y buscar empleados, con comparación vs búsqueda secuencial.
- `src/act4ed/Main.java`: Demostración de operaciones y del caso práctico.

## Diagramas

Diagrama de clases simplificado:

```mermaid
classDiagram
    class Node~T~ {
      T value
      Node~T~ left
      Node~T~ right
    }
    class ArbolBinario~T~ {
      - Node~T~ root
      + insert(T)
      + remove(T)
      + contains(T) boolean
      + inorder() List~T~
      + preorder() List~T~
      + postorder() List~T~
    }
    class Employee {
      - int id
      - String name
      - String department
      + compareTo(Employee)
    }
    class EmployeeDirectory {
      - ArbolBinario~Employee~ tree
      - List~Employee~ list
      + add(Employee)
      + findById(int) Employee
      + sequentialFindById(int) Employee
      + listInOrder() List~Employee~
    }
    ArbolBinario --> Node
    EmployeeDirectory --> ArbolBinario
```

Ejemplo de árbol tras insertar [50, 30, 70, 20, 40, 60, 80]:

```mermaid
graph TD
    A50((50))
    B30((30))
    C70((70))
    D20((20))
    E40((40))
    F60((60))
    G80((80))
    A50 --> B30
    A50 --> C70
    B30 --> D20
    B30 --> E40
    C70 --> F60
    C70 --> G80
```

## Cómo compilar y ejecutar

Requisitos: Java 17+ (cualquier JDK reciente funciona).

En Windows PowerShell:

```powershell
javac -d out src/act4ed/tree/*.java src/act4ed/employee/*.java src/act4ed/Main.java
java -cp out act4ed.Main
```

## Explicación del funcionamiento

- El BST mantiene el orden por medio de `Comparable<T>`. La búsqueda y eliminación siguen el algoritmo clásico (en eliminación, si el nodo tiene dos hijos se reemplaza con el sucesor inorden).
- Los recorridos (inorden, preorden, postorden) se implementan recursivamente y se devuelven como listas.
- Para empleados, el BST ordena por `id`. El directorio también guarda una lista lineal para comparar con búsqueda secuencial.

## Evidencias de ejecución

Salida real al ejecutar el programa:

```text
DEMOSTRACIÓN: Árbol Binario (BST) numérico

Árbol después de inserciones:
                80
        70
                60
50
                40
        30
                20

Inorden:   [20, 30, 40, 50, 60, 70, 80]
Preorden:  [50, 30, 20, 40, 70, 60, 80]
Postorden: [20, 40, 30, 60, 80, 70, 50]
Buscar 60 -> true (pasos=3)

Eliminar 30 (nodo con dos hijos)

                80
        70
                60
50
        40
                20

Inorden tras eliminar: [20, 40, 50, 60, 70, 80]

============================================================

DEMOSTRACIÓN: Gestión de Empleados con BST

Empleados en orden por ID (inorden):
 - Employee{id=1001, name='Luis', dept='TI'}
 - Employee{id=1003, name='Diego', dept='TI'}
 - Employee{id=1005, name='Ana', dept='Ventas'}
 - Employee{id=1007, name='Sofía', dept='Finanzas'}
 - Employee{id=1009, name='Marta', dept='RH'}

Buscar ID 1007 -> BST: Employee{id=1007, name='Sofía', dept='Finanzas'} (pasos=3), Secuencial: Employee{id=1007, name='Sofía', dept='Finanzas'}
Buscar ID 1011 inexistente -> BST pasos=2, Secuencial: null

Conclusión: El BST reduce comparaciones respecto a una búsqueda secuencial, especialmente cuando crece el número de empleados.
```

## Reflexión

Los árboles binarios de búsqueda permiten insertar, buscar y eliminar en tiempo promedio O(log n) manteniendo los datos ordenados. Son base de estructuras más avanzadas (AVL, Red-Black, B-Trees) usadas en bases de datos, compiladores y motores de búsqueda. En gestión de empleados, al crecer el número de registros, un BST reduce comparaciones frente a una búsqueda lineal, mejorando rendimiento sin requerir estructuras externas.

<!-- Fin del documento -->
