# 이상현 202130421

## 6월 7일 강의


### 멀티태스킹 ( multi-tasking ) 개념
- 멀티태스킹
    - 여러 개의 작업(태스크)이 동시에 처리되는 것

# 스레드와 운영체제

 스레드(thread)
- 운영체제에 의해 관리되는 하나의 작업 혹은 태스크
- 스레드와 태스크(혹은 작업)은 바꾸어 사용해도 무관

 멀티스레딩(multi-threading)
- 여러 스레드를 동시에 실행시키는 응용프로그램을 작성하는 기법

### 스레드 구성
- 스레드 코드
  - 작업을 실행하기 위해 작성한 프로그램 코드
  - 개발자가 작성
- 스레드 정보
  - 스레드 명, 스레드 ID, 스레드의 실행 소요 시간, 스레드의 우선 순위 등
  - 운영체제가 스레드에 대해 관리하는 정보



### 멀티태스킹 구현 기술
- 멀티프로세싱(multi-processing)
  - 하나의 응용프로그램이 여러 개의 프로세스를 생성하고, 각 프로세스가 하나의 작업을 처리하는 기법
  - 각 프로세스 독립된 메모리 영역을 보유하고 실행
  - 프로세스 사이의 문맥 교환에 따른 과도한 오버헤드와 시간 소모의 문제점
- 멀티스레딩(multi-threading)
  - 하나의 응용프로그램이 여러 개의 스레드를 생성하고, 각 스레드가 하나의 작업을 처리하는 기법
  - 하나의 응용프로그램에 속한 스레드는 변수 메모리, 파일 오픈 테이블 등 자원으로 공유하므로, 문맥 교환에 따른 오버헤드가 매우 작음
  - 현재 대부분의 운영체제가 멀티스레딩을 기본으로 함

 자바 스레드(Thread)와 JVM

### 자바 스레드
- 자바 가상 기계(JVM)에 의해 스케쥴되는 실행 단위의 코드 블럭
- 스레드의 생명 주기는 JVM에 의해 관리됨: JVM은 스레드 단위로 스케쥴링

### JVM과 자바의 멀티스레딩
- 하나의 JVM은 하나의 자바 응용프로그램만 실행
- 자바 응용프로그램이 시작될 때 JVM이 함께 실행됨
  - 자바 응용프로그램이 종료하면 JVM도 함께 종료함
- 응용프로그램은 하나 이상의 스레드로 구성 가능

# Thread 클래스를 상속받아 스레드 만들기

 Thread 클래스를 상속받아 스레드 만들기
- Thread 클래스 상속
- run() 메소드 오버라이딩
  - run() 메소드는 스레드 코드를 포함
  - run() 메소드에서 스레드 실행 시작
 스레드 객체 생성 및 시작
- 스레드 객체 생성
  - Thread 클래스의 생성자 이용
- 스레드 시작
  - start() 메소드 호출
    - JVM에 의해 스케쥴되기 시작

 Thread 클래스의 주요 메소드
- `Thread()`
  - 스레드 객체 생성
- `Thread(Runnable target)`
  - Runnable 객체를 이용하여 스레드 객체 생성
- `Thread(String name)`
  - 이름이 있는 스레드 객체 생성
- `void run()`
  - 스레드 코드 작성을 위한 메소드
- `void start()`
  - JVM에게 스레드 실행 시작 요청
- `void interrupt()`
  - 스레드 강제 종료
- `static void yield()`
  - 다른 스레드에게 실행 양보
- `void join()`
  - 스레드가 종료할 때까지 대기
- `long getId()`
  - 스레드의 ID값 반환
- `String getName()`
  - 스레드의 이름 반환
- `int getPriority()`
  - 스레드의 우선순위 값 반환
- `void setPriority(int priority)`
  - 스레드의 우선순위 값을 변경
- `Thread.State getState()`
  - 스레드의 상태 값 반환
- `static void sleep(long millis)`
  - 스레드를 지정한 시간만큼 재우기
- `static Thread currentThread()`
  - 현재 실행 중인 스레드 객체의 레퍼런스 반환

### 예제 코드
- Thread를 상속받아 1초 단위로 초 시간을 출력하는
TimerThread 스레드 작성 사례

```java
// TimerThread 클래스 선언
class TimerThread extends Thread {
    // 타이머 값이 출력되는 레이블
    private Label timerLabel;

    // 생성자
    public TimerThread(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    // 스레드 코드 run()이 종료하면 스레드 종료
    @Override
    public void run() {
        // 타이머 카운트 값
        int n = 0;

        while (true) {
            // 타이머 값을 레이블에 설정
            timerLabel.setText(Integer.toString(n));
            n++; // 카운트 증가
            try {
                Thread.sleep(1000); // 1초 동안 잠을 잔 후 실행
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
```

### 예제코드 2
- Thread를 상속받아 1초 단위 타이머 스레드 만들기

```java
// TimerThread를 사용하는 예제
public class ThreadTimerEx extends JFrame {
    public ThreadTimerEx() {
        setTitle("Thread를 상속받은 타이머 스레드 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨테이너 설정
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 타이머 값을 출력할 레이블 생성
        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Gothic", Font.ITALIC, 20));
        c.add(timerLabel);

        // 타이머 스레드 객체 생성
        TimerThread th = new TimerThread(timerLabel);

        setSize(250, 150);
        setVisible(true);

        // 타이머 스레드 시작
        th.start();
    }

    public static void main(String[] args) {
        new ThreadTimerEx();
    }
}
```
 Main 스레드
- main 스레드
    - JVM이 응용프로그램을 실행할 때 디폴트로 생성되는 스레드
    - main()메소드
    - main 메소드가 종료하면 main 쓰레드 종료

 스레드 동기화(Thread Synchronization)

### 멀티스레드 프로그램 작성시 주의점
- 다수의 스레드가 공유 데이터에 동시에 접근하는 경우 공유 데이터의 값에 예상치 못한 결과가 발생할 수 있음.

### 스레드 동기화
- **동기화란?**
    - 스레드 사이의 실행 순서를 제어하고 공유 데이터에 대한 접근을 원활하게 하는 기법.
- 멀티스레드의 공유 데이터의 동시 접근 문제를 해결하기 위한 방법:
    1. 공유 데이터를 접근하는 모든 스레드를 한 줄로 세우기.
    2. 한 스레드가 공유 데이터에 대한 작업을 끝낼 때까지 다른 스레드가 대기하도록 함.

### 자바의 스레드 동기화 방법 - 2가지
1. `synchronized` 키워드로 동기화 블록 지정
2. `wait()`-`notify()` 메소드로 스레드의 실행 순서를 제어함.

### synchronized 블록 지정

### synchronized 키워드
- 스레드가 독점적으로 실행해야 하는 부분(동기화 코드)을 표시하는 키워드.
- 일제 영역(critical section) 표기 키워드.

### synchronized 블록 지정 방법
- 메소드 전체 혹은 코드 블록을 synchronized 블록으로 지정할 수 있음.

### synchronized 블록이 실행될 때
- 먼저 실행한 스레드가 모니터 소유.
    - 모니터: 해당 객체를 독점적으로 사용할 수 있는 권한.
- 모니터를 소유한 스레드가 모니터를 내놓을 때까지 다른 스레드는 대기해야 함.

## 5월 31일 강의


 스윙 컴포넌트의 이해/정의
스윙 컴포넌트는 자바에서 GUI를 구축하기 위해 제공하는 경량 컴포넌트입니다. 모든 스윙 컴포넌트는 `JComponent` 클래스를 상속받아 다양한 UI 요소를 제공합니다. 이를 통해 개발자는 윈도우, 버튼, 텍스트 필드 등 다양한 인터페이스 요소를 쉽게 구현할 수 있습니다.

 자바의 GUI 프로그래밍 방법

1. 컴포넌트 기반 GUI 프로그래밍
- **스윙 컴포넌트**를 이용하여 쉽게 GUI를 구축
- 자바에서 제공하는 컴포넌트의 한계를 벗어나지 못함

2. 그래픽 기반 GUI 프로그래밍
- **그래픽**을 이용하여 GUI 구축
- 개발자가 직접 그래픽으로 화면을 구성하는 부담
- 다양한 GUI를 구축할 수 있는 장점
- GUI의 처리 실행 속도가 빨라, 게임 등에 주로 이용

 추가 설명
- **사용자 인터페이스 제공**: 그래픽 기반 GUI(Graphical User Interface)는 아이콘, 버튼, 윈도우 등 시각적 요소를 통해 사용자와 컴퓨터가 상호작용할 수 있게 하는 인터페이스입니다.
- **사용자 경험 향상**: GUI는 명령어 기반 인터페이스(CLI)보다 직관적이고 사용하기 쉬워, 사용자 경험을 크게 향상시킵니다. 비전문가도 컴퓨터를 쉽게 사용할 수 있게 합니다.
- **그래픽 요소 활용**: GUI는 그래픽 요소를 활용해 정보를 더 명확하고 이해하기 쉽게 전달합니다. 이를 통해 사용자들은 복잡한 작업도 쉽게 수행할 수 있습니다.
- **운영 체제와 애플리케이션 통합**: GUI는 대부분의 현대 운영 체제와 애플리케이션에서 표준으로 사용되며, 윈도우, 맥OS, 리눅스 등의 시스템에서 다양한 소프트웨어와 함께 작동합니다.

컴포넌트 기반 GUI 프로그래밍에서 사용되는 스윙 컴포넌트

### 스윙 컴포넌트의 공통 메소드 - JComponent의 메소드

#### 컴포넌트의 모양과 관련된 메소드
```java
- void setForeground(Color): 전경색 설정
- void setBackground(Color): 배경색 설정
- void setOpaque(boolean): 불투명성 설정
- void setFont(Font): 폰트 설정
- Font getFont(): 폰트 리턴
```

#### 컴포넌트의 위치와 크기에 관련된 메소드
```java
- int getWidth(): 폭 리턴
- int getHeight(): 높이 리턴
- int getX(): x 좌표 리턴
- int getY(): y 좌표 리턴
- Point getLocationOnScreen(): 스크린 좌표상에서의 컴포넌트 좌표 리턴
- void setLocation(int, int): 위치 지정
- void setSize(int, int): 크기 지정
```

#### 컴포넌트의 상태와 관련된 메소드
```java
- void setEnabled(boolean): 컴포넌트 활성화/비활성화 설정
- void setVisible(boolean): 컴포넌트 보이기/숨기기 설정
- boolean isVisible(): 컴포넌트의 보이는 상태 리턴
```

#### 컨테이너를 위한 메소드
```java
- Component add(Component): 자식 컴포넌트 추가
- void remove(Component): 자식 컴포넌트 제거
- void removeAll(): 모든 자식 컴포넌트 제거
- Component[] getComponents(): 자식 컴포넌트 배열 리턴
- Container getParent(): 부모 컨테이너 리턴
- Container getTopLevelAncestor(): 최상위 부모 컨테이너 리턴
```

#### JComponent

1. **스윙 컴포넌트는 모두 상속받는 슈퍼 클래스**: JComponent는 스윙 컴포넌트들이 공통으로 상속받는 `추상 클래스`입니다.
2. **스윙 컴포넌트들이 상속받는 공통 메소드와 상수 구현**: JComponent는 다양한 메소드와 상수를 구현하여 스윙 컴포넌트들이 이를 활용할 수 있게 합니다.
3. **JComponent의 주요 메소드 사례**:
   - `setBackground(Color bg)`: 컴포넌트의 배경색을 설정합니다.
   - `setForeground(Color fg)`: 컴포넌트의 전경색(텍스트 색상 등)을 설정합니다.
   - `setFont(Font f)`: 컴포넌트의 폰트를 설정합니다.
   - `repaint()`: 컴포넌트를 다시 그립니다.
   - `addMouseListener(MouseListener l)`: 마우스 이벤트를 감지하기 위해 리스너를 추가합니다.

### 실제활용 예제 코드
```java
import javax.swing.*;
import java.awt.*;

public class CustomComponent extends JComponent {
    
    // 이 메서드는 컴포넌트를 그릴 때 호출됩니다.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 부모 클래스의 기본 페인팅 작업 수행
        g.setColor(Color.BLUE); // 색상을 파란색으로 설정
        g.fillRect(10, 10, 100, 100); // (10, 10) 위치에 100x100 크기의 파란색 사각형 그리기
    }

    // 메인 메서드: 애플리케이션 실행 진입점
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom JComponent"); // 새로운 프레임 생성
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 클릭 시 애플리케이션 종료
        frame.add(new CustomComponent()); // 커스텀 컴포넌트를 프레임에 추가
        frame.setSize(200, 200); // 프레임 크기를 200x200으로 설정
        frame.setVisible(true); // 프레임을 화면에 표시
    }
}
```

 레이블 생성 예
1. **문자열 레이블 생성**
```java
JLabel textLabel = new JLabel("사랑합니다");
```

 이미지 레이블 생성
1. **이미지 파일로부터 이미지를 읽기 위해 Imageicon 클래스 사용**
2. **다를 수 있는 이미지 : png , gif , jpg**
```java
--> sunset.jpg의 경로명이 "images/sumset.jpg"인 경우
```

 수평 정령 값을 가진 레이블 컴포넌트 생성

1. **수평 정렬로, 문자열과 이미지를 모두 가진 레이블**
```java
Imageicon image = new Imageicon("images/sunset.jpg");
JLabel textLabel = new JLabel("사랑합니다"), image, SwingConstants CENTER");
```

 이미지 버튼 만들기

### 버튼에 3개의 이미지 등록하기

- 하나의 버튼에 3개의 이미지를 등록합니다.
- 마우스 조작에 따라 3개의 이미지 중 적절한 이미지가 자동으로 출력됩니다.

### 3개의 버튼 이미지

- **normallcon**
  - 버튼의 보통 상태(디폴트) 때 출력되는 이미지
  - 생성자에 이미지 아이콘을 전달하거나 `JButton`의 `setIcon(normalIcon)` 메소드를 사용합니다.
  
- **rolloverlcon**
  - 버튼에 마우스가 올라갈 때 출력되는 이미지
  - 이미지 설정 메소드: `JButton`의 `setRolloverIcon(rolloverIcon)`을 사용합니다.

- **pressedicon**
  - 버튼을 누른 상태 때 출력되는 이미지
  - 이미지 설정 메소드: `JButton`의 `setPressedIcon(pressedIcon)`을 사용합니다.

### 이미지 설정

#### 이미지 로딩

1. **필요한 이미지를 로딩합니다. 예시:**
```java
ImageIcon normalIcon = new ImageIcon("images/normalIcon.gif");
ImageIcon rolloverIcon = new ImageIcon("images/rolloverIcon.gif");
ImageIcon pressedIcon = new ImageIcon("images/pressedIcon.gif");
```

2. **버튼에 이미지 등록**
- JButton의 메소드를 호출하여 이미지를 등록합니다. 예시:
```java
JButton button = new JButton("테스트버튼", normalIcon); // normalIcon 설정
button.setRolloverIcon(rolloverIcon);
button.setPressedIcon(pressedIcon);
```

3. **실행 중에 이미지 변경**
- 실행 중에는 다른 이미지로 변경할 수 있습니다. 예시:
```java
// rolloverIcon으로 변경
// pressedIcon으로 변경
ImageIcon newIcon = new ImageIcon("images/newIcon.gif");
button.setIcon(newIcon); // 디폴트 이미지 변경
```

 체크박스의 Item 이벤트 처리

### Item 이벤트

- 체크박스의 선택 상태에 변화가 생길 때 발생하는 이벤트입니다. 이는 사용자가 마우스나 키보드로 체크박스를 선택하거나 해제하거나, 프로그램에서 체크박스를 선택하거나 해제하여 체크 상태에 변화가 생길 때 발생합니다.

### 체크박스 설정

```java
JCheckBox checkbox = new JCheckBox("A");
checkbox.setSelected(true); // 선택 상태로 변경
```

### 이벤트 처리

- Item 이벤트가 발생하면 `ItemEvent` 객체가 생성됩니다.
- `ItemListener` 리스너를 이용하여 이벤트를 처리합니다.

#### ItemListener의 추상 메소드

- `void itemStateChanged(ItemEvent e)`: 체크박스의 선택 상태가 변하는 경우 호출됩니다.

#### ItemEvent의 주요 메소드

- `int getStateChange()`: 체크박스가 선택된 경우 `ItemEvent.SELECTED`, 해제된 경우 `ItemEvent.DESELECTED`를 리턴합니다.
- `Object getItem()`: 이벤트를 발생시킨 아이템 객체를 리턴합니다. 체크박스의 경우 `JCheckBox` 컴포넌트의 레퍼런스를 리턴합니다.


```java
checkbox.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 체크박스가 선택된 경우 처리
            System.out.println("체크박스가 선택되었습니다.");
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // 체크박스가 해제된 경우 처리
            System.out.println("체크박스가 해제되었습니다.");
        }
    }
});
```
 JRadioButton으로 라디오버튼 만들기

### JRadioButton의 용도

JRadioButton은 버튼 그룹을 형성하고, 그룹에 속한 버튼 중 하나만 선택되는 라디오버튼입니다.

#### 체크박스와의 차이점

- 체크박스는 각각 선택 또는 해제가 가능하지만,
- 라디오버튼은 그룹에 속한 버튼 중 하나만 선택됩니다.

### 라디오버튼 생성

다양한 생성자를 사용하여 라디오버튼을 생성할 수 있습니다:

- `JRadioButton()`: 텍스트 없이 기본 라디오버튼을 생성합니다.
- `JRadioButton(Icon icon)`: 아이콘 이미지가 있는 라디오버튼을 생성합니다.
- `JRadioButton(Icon icon, boolean selected)`: 아이콘 이미지가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.

- `JRadioButton(String text)`: 텍스트가 있는 라디오버튼을 생성합니다.
- `JRadioButton(String text, boolean selected)`: 텍스트가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.
- `JRadioButton(String text, Icon icon)`: 텍스트와 아이콘 이미지가 있는 라디오버튼을 생성합니다.
- `JRadioButton(String text, Icon icon, boolean selected)`: 텍스트와 아이콘 이미지가 있는 라디오버튼을 생성하며, 선택 상태를 초기화할 수 있습니다.

### 라디오버튼 생성 및 Item 이벤트 처리

#### 버튼 그룹과 라디오버튼 생성 과정

```java
ButtonGroup group = new ButtonGroup();
JRadioButton apple = new JRadioButton("Apple");
JRadioButton pear = new JRadioButton("Pear");
JRadioButton cherry = new JRadioButton("Cherry");

group.add(apple);
group.add(pear);
group.add(cherry);

container.add(apple);
container.add(pear);
container.add(cherry);
```

### 라디오버튼에 Item 이벤트 처리
라디오버튼의 선택 상태 변경을 감지하기 위해 ItemListener를 사용합니다. 이벤트가 발생하면 setSelected()를 호출하여 선택 상태를 변경할 수 있습니다.

```java
apple.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 라디오버튼이 선택되었을 때 처리
        }
    }
});
```
 JTextField로 한 줄 입력 창 만들기

JTextField는 한 줄의 문자열을 입력 받는 창, 즉 텍스트필드를 나타냅니다. 사용자가 텍스트를 입력하는 도중 <Enter> 키가 입력되면 Action 이벤트가 발생합니다. 입력 가능한 문자 개수와 입력 창의 크기는 서로 다를 수 있습니다.

### 텍스트필드 생성

다양한 생성자를 사용하여 텍스트필드를 생성할 수 있습니다:

- `JTextField()`: 빈 텍스트필드를 생성합니다.
- `JTextField(int cols)`: 입력 열의 갯수가 cols개인 텍스트필드를 생성합니다.
- `JTextField(String text)`: 초기 텍스트로 문자열 text를 갖는 텍스트필드를 생성합니다.
- `JTextField(String text, int cols)`: 입력 열의 수는 cols이고 초기 텍스트로 문자열 text를 갖는 텍스트필드를 생성합니다.

#### 초기값이 "컴퓨터공학과"인 텍스트필드 생성 예
```java
JTextField tf2 = new JTextField("컴퓨터공학과");
```

 JList<E>

JList는 하나 이상의 아이템을 보여주고 사용자가 아이템을 선택할 수 있도록 하는 리스트입니다. Java 7부터는 제네릭 리스트로 변경되어 `<E>`에 지정된 타입의 객체만 저장할 수 있습니다. 

JList를 JScrollPane에 삽입하여 스크롤이 가능하도록 만들 수 있습니다.

### 리스트 생성

다양한 생성자를 사용하여 리스트를 생성할 수 있습니다:

- `JList()`: 빈 리스트를 생성합니다.
- `JList<E>(List<E> listData)`: 리스트에 아이템을 공급받는 리스트를 생성합니다.
- `JList<E>(E[] listData)`: 리스트에 아이템을 배열로부터 공급받는 리스트를 생성합니다.

#### 예시: 9개의 과일 이름 문자열이 든 리스트 만들기

```java
String[] fruits = {"apple", "banana", "kiwi", "mango", "pear", "peach", "berry", "strawberry", "blackberry"};
JList<String> strList = new JList<String>(fruits);
```

## 메뉴 구성

### **메뉴 만들기에 필요한 스윙 컴포넌트는 다음과 같습니다:**

- **메뉴아이템 (MenuItems)**: 여러 개의 메뉴 아이템을 가지는 항목입니다.
- **메뉴 (Menus)**: 여러 개의 메뉴 아이템을 가지는 그룹입니다.
- **메뉴바 (JMenuBar)**: 여러 개의 메뉴를 붙이는 바이며, 프레임에 부착됩니다.
- **분리선 (Separator)**: 메뉴 아이템 사이의 분리선으로, separator라고도 합니다. Menu 클래스의 `addSeparator()` 메소드를 호출하여 삽입할 수 있습니다.

### **메뉴 만드는 과정**

1. **JMenuBar 컴포넌트 생성**: 메뉴바를 생성합니다.
2. **JMenu 컴포넌트를 생성하여 JMenuBar에 붙임**: 메뉴를 생성하고 메뉴바에 추가합니다.
3. **JMenuItem 컴포넌트를 생성하여 JMenu에 붙임**: 각 메뉴에 메뉴 아이템을 추가합니다. 여러 개의 메뉴와 메뉴 아이템을 생성할 수 있습니다.
4. **JMenuBar 컴포넌트를 JFrame에 붙임**: 생성한 메뉴바를 JFrame에 추가합니다.

```java
JMenuBar mb = new JMenuBar(); // 1. JMenuBar 생성
JMenu screenMenu = new JMenu("Screen"); // 2. JMenu 생성
mb.add(screenMenu); // 2. JMenu를 JMenuBar에 추가
screenMenu.add(new JMenuItem("Load")); // 3. JMenuItem 추가
screenMenu.add(new JMenuItem("Hide"));
screenMenu.add(new JMenuItem("ReShow"));
screenMenu.addSeparator();
screenMenu.add(new JMenuItem("EXIT"));
frame.setMenuBar(mb); // 4. JMenuBar를 JFrame에 추가
```

### 메뉴 아이템에 Action 리스너 설정
```java
JMenuItem item = new JMenuItem("Load");
item.addActionListener(new MenuActionListener());
screenMenu.add(item);

// Action 이벤트를 처리할 리스너 작성
class MenuActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // 사용자가 Load 메뉴 아이템을 선택하는 경우 처리할 작업을 구현합니다.
    }
}
```

## 팝업 다이얼로그, JOptionPane

### 팝업 다이얼로그
- 사용자에게 메시지를 전달하거나 문자열을 간단히 입력받는 용도
- JOptionPane 클래스를 이용하여 생성
  - static 타입의 간단한 메소드 이용

### 입력 다이얼로그 - `JOptionPane.showInputDialog()`
- 한 줄을 입력 받는 다이얼로그
- `static String JOptionPane.showInputDialog(String msg)`
  - `msg`: 다이얼로그 메시지
  - `리턴 값`: 사용자가 입력한 문자열, 취소 버튼이 선택되거나 창이 닫히면 null 리턴

```java
String name = JOptionPane.showInputDialog("이름을 입력하세요");
// name: 사용자가 입력한 값 (예: "Java Kim")
```



## 5월 24일 강의

이벤트 기반 프로그래밍
    이벤트의 발생에 의해 프로그램 흐름이 결정되는 방식
        이벤트가 발생하면 이벤트를 처리하는 루틴 실행
        실행될 코드는 이벤트의 발생에 의해 견적으로 결정
    반대되는 개념: 배치 실행
        프로그램의 개발자가 프로그램의 흐름을 결정하는 방식
    이벤트 종류
        사용자의 입력 : 마우스 드래그, 마우스 클릭, 키보드 누름 등
        센서로부터의 입력 : 네트워크로부터 데이터 송수신

이벤트 기반 응용 프로그램의 구조
    각 이벤트마다 처리하는 리스너 코드 보유

이벤트가 처리되는 과정
    이벤트 발생
        예 : 마우스의 움직임 혹은 키보드입력
    이벤트 객체 생성
        현재 발생한 이벤트에 대한 정보를 가진 객체
    응용프로그램에 작성된 이벤트 리스너 찾기
    이벤트 리스너 실행
        리스너에 이벤트 객체 전달
        리스너 코드 실행

이벤트 객체
    발생한 이벤트에 관한 정보를 가진 객체
    이벤트 리스너에 전달됨
        이벤트 리스너 코드가 발생한 이벤트에 대한 상황을 파악할 수 있게 함

이벤트 객체가 포함하는 정보
    이벤트 종류와 이벤트 소스
    이벤트가 발생한 화면 좌표 및 컴포넌트 내 좌표
    이벤트가 발생한 버튼이나 메뉴 아이템의 문자열
    클릭된 마우스 버튼 번호 및 마우스의 클릭 횟수
    키의 코드 값과 문자 값
    체크박스, 라디오버튼 등과 같은 컴포넌트에 이벤트가 발생하였다면 체크 상태

이벤트 소스를 알아내는 메소드
    object getSource()
        발생한 이벤트의 소스 컴포넌트 리턴
        object 타입으로 리턴하므로 캐스팅하여 사용
        모든 이벤트 객체에 대해 적용

이벤트 리스너
    이벤트를 처리하는 자바 프로그램 코드, 클래스로 작성

이벤트와 이벤트 리스너 성택
    버튼 클릭을 처리하고자 하는 경우
        이벤트 : action 이벤트, 이벤트 리스너
    이벤트 리스너 클래스 작성 : actionListener 인터페이스 구현

이벤트 리스너 등록
    이벤트를 받아 처리하고자 하는 컴포넌트에 리스너 등록

3가지 방법
    독립 클래스로 작성
        이벤트 리스너를 완전한 클래스로 작성
        이벤트 리스너를 여러 곳에서 사용할 때 적합
    내부 클래스로 작성
        클래스 안에 멤버처럼 클래스 작성
        이벤트 리스너를 특정 클래스에서만 사용할 때 적합
    익명 클래스로 작성
        클래스의 이름 없이 간단한 리스너 작성
        클래스 조차 만들 필요 없이 리스너 코드가 간단한 경우에 적합
        
간단한 리스너의 경우 익명 클래스 사용 추천
- 리스너가 매우 간단한 경우 익명 클래스를 사용하여 `코드를 간결하게 유지`, 메소드의 개수가 적은 경우에 사용

Action리스너를 구현하는 익명의 이벤트 리스너 작성
- 이름을 가진 클래스를 작성하지 않고도 익명 클래스를 사용하여 간단한 이벤트 리스너를 작성, `단순한 이벤트 처리`에 적합

 어댑터 클래스
```java
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener extends MouseAdapter {
    // 클릭 이벤트 처리
    public void mouseClicked(MouseEvent e) {
        // 클릭 이벤트 처리 로직 작성
    }
}

```
`위 코드는 MouseAdapter 클래스를 이용해서 간단하게 마우스 이벤트를 처리하는 예제 :  MyMouseListener 클래스를 만들어서 MouseAdapter를 상속받아 클릭 이벤트 처리하는 메소드를 오버라이드 하고, 필요한 이벤트에만 집중해서 코드를 간결하게 작성`

### 이벤트 리스너 구현에 따른 부담
- 리스너의 추상 메소드를 모두 구현해야하는 부담
- 예: 마우스 리스너에서 마우스가 눌러지는 경우만 처리하고자 하는 경우에도 나머지 4개의 메소드를 모두 구현하는 부담

### 어댑터 클래스
- 리스너의 모든 메소드를 단순 리턴하도록 만든 클래스
- 마우스 어댑터 사용 예시
 키 이벤트와 포커스

**키 이벤트 발생 시점**
- 키를 누르는 순간
- 누른 키를 떼는 순간
- Unicode 키의 경우, 누른 키를 떼는 순간

**키 이벤트를 받을 수 있는 조건**
- `모든 컴포넌트`
- 현재 포커스를 가진 컴포넌트가 키 이벤트를 독점

**포커스**
- 컴포넌트나 응용프로그램이 키 이벤트를 `독점하는 권한`

**유니코드 키의 특징**
1. `국제 산업 표준`
2. 전 세계의 문자를 컴퓨터에서 `일관되게 표현`하기 위한 `코드 체계`
3. 문자들에 대해서만 `키 모드 값 정의`

**가상 키와 입력된 키 판별**

*key*이벤트 객체
1. 입력된 키 정보를 가진 이벤트 객체
2. *key*이벤트 객체의 메소드로 입려된 키 판별

*key*이벤트 객체의 메소드로 입렫된 키 판별
1. 키의 `유니코드 문자 값 리턴`
2. `유니코드 문자 키인 경우`에만 의미 있음
3. 입력된 키를 판별하기 위해 `문자 값과 비교`하면 됨

*int key*이벤트 
1. 유니코드 키 포함
2. 모든 키에 대한 `정수형 키 코드 리턴`
3. 입력된 키를 `판별`하기 위해
4. 가상키 값과 `비교`하기 위해

**가상 키**
1. 가상키는 *key*이벤트 클래스에 `상수로 선언`
2. 설명 : *key*Event에서의 가상 키는 사용자 입력 장치에 실제로 `존재하지 않지만`, 프로그래밍에서 논리적으로 사용되는 `키`를 말합니다. 이는 주로 특수 키나 기능 키 등을 포함하며, 프로그램에서 `이벤트 핸들링을 위해 활용`됩니다. 사용자의 키보드나 마우스 입력을 프로그램이 인식하고, 그에 따른 동작을 수행할 때 주로 사용됩니다.



## 5월 17일 강의

FlowLayout 배치관리자
    컴포넌트가 삽입되는 순서대로 왼쪽에서 오른쪽으로 배치
    배치할 공간이 없으면 아래로 내려와서 반복한다
BorderLayout 배치관리자
    컨테이너의 공간을 동, 서, 남, 북 중앙 5개 영역으로 나눔
    5개 영역 중 응용프로그램에서 컴포넌트 배치
GridLayout 배치관리자
    컨테이너를 프로그램에서 설정한 동일한 크기의 2차원 격자로 나눔
    컴포넌트는 삽입 순서대로 좌에서 우로, 다시 위에서 아래로 배치
CardLayout
    컨테이너의 공간에 카드를 쌓아 놓은 듯이 컴포넌트를 포개어 배치

컨테이너 디폴트 배치관리자
    컨테이너 생성시 자동으로 생성되는 배치관리자

컨테이너에 새로운 배치관리자 설정
    setLayout(LayoutManager Im) 메소드 호출
    Im을 새로운 배치관리자로 설정

배치방법
    컨테이너 공간을 5 구역으로 분할, 배치
        동, 서, 남, 북, 중앙
    배치방법
        add(Component comp, int index)
            comp를 index의 공간배치

생성자
    rows : 격자의 행수 (디폴트 1)
    cols : 격자의 열수 (디폴트 1)
    hGap : 좌우 두 컴포넌트 사이의 수평간격, 픽셀단위(디폴트 0)
    vGap : 좌우 두 컴포넌트 사이의 수평간격, 픽셀단위(디폴트 0)
    rows x clos 만큼의 셀을 가진 격자로 컨테이너 공간을 분할, 배치

배치관리자가 없는 컨테이너가 필요한 경우
    응용프로그램에서 직접 컴포넌트의 크기와 위츠를 결정하고자 하는 경우
        1. 컴포넌트의 크기나 위츠를 개발자 임의로 결정하고자 하는 경우
        2. 게임 프로그램과 같이 시간이나 마우스/키보드의 입력에 따라 컴포넌트들의 위치와 크기가 수시로 변하는 경우
        3. 여러 컴포넌트들이 서로 겹쳐 출력하고자 하는 경우

배치관리자가 없는 컨테이너에 컴포넌트를 삽입할 때
    프로그램에서 컴포넌트의 절대 크기와 위치 설정
    컴포넌트들이 서로 겹치게 할 수 있음

## 4월 19일 강의

추상 메소드
    abstract로 선언된 메소드, 메소드의 코드는 없고 원형만 선언

추상 클래스
    추상 메소드를 가지며, abstract로 선언된 클래스
    추상 메소드 없이, avstract로 선언한 클래스

추상 클래스 상속
    추상 클래스를 상속받으면 추상 클래스가 됨
    서브 클래스도 abstract로 선언해야 함

추상 클래스 구현
    서브 클래스에서 슈퍼 클래스의 추상 메소드 구현(오버라이딩)
    추상 클래스를 구현한 서브 클래스는 추상 클래스 아님

추상 클래스의 목적
    상속을 위한 슈퍼 클래스로 활용하는 것
    서브 클래스에서 추상 메소드 구현
    다형성 실현

자바의 인터페이스
    클래스가 구현해야 할 메소드들이 선언되는 추상형
    인터페이스 선언
        interface 키워드로 선언
        Ex) public interface SeriaDiver(...)

자바 인터페이스에 대한 변화
    Java 7까지
        상수와 추상메소드 포함
        default 메소드 포함(Java 8)
        private 메소드 포함(Java 9)
        static 메소드 포함(Java 9)

인터페이스의 구성 요소들
    상수
    추상메소드
    default 메소드
    private 메소드
    static 메소드

여전히 인터페이스에는 필드(멤버 변수) 선언 불가

인터페이스 간에 상속 가능
    인터페이스를 상속하여 확장된 인터페이스 작성 가능
        extends 키워드로 상속 선언

인터페이스 다중 상속 허용

인터페이스의 추상 메소드를 모두 구현한 클래스 작성
    implements 키워드 사용
    여러 개의 인터페이스 동시 구현 가능

인터페이스 구현 사례
    PhoneInterface 인터페이스를 구현한 SamsungPhone 클래스
    SamsungPhone 클래스는 PhoneInterface의 default 메소드 상속

패키지
    서로 관련된 클래스와 인터페이스를 컴파일한 클래스 파일들을 묶어 놓은 디렉터리
    하나의 응용프로그램은 한 개 이상의 패키지로 작성
    패키지는 jar 파일로 압축할 수 있음

모듈
    여러 패키지와 이미지 등의 자원을 모아 놓은 컨테이너
    하나의 모듈을 하나의 .jmod 파일에 저장

Java 9부터 모듈화 도입
    플랫폼의 모듈화
        Java 9부터 자바 API의 모든 클래스들(자바 실행 환경)을 패키지 기반에서 모듈들로 완전히 재구성
    응용프로그램의 모듈화
        클래스들은 패키지로 만들고, 다시 패키지

모듈화의 목적
    Java 9부터 자바 API를 여러 모둘(99개)로 분할
        Java 8까지는 rt.jar의 한 파일에 모든 API 저장
    응용프로그램이 실행할 때 꼭 필요한 모듈들로만 실행 환경 구축
        메모리 자원이 열악한 작은 소형 기기에 꼭 필요한 모듈로 구성된 작은 크기의 실행 이미지를 만들기 위함
    
모듈의 현실
    Java 9부터 전면적으로 도입
    복잡한 개념
    큰 자바 응용프로그램에는 개발, 유지보수 등에 적함
    현실적으로 모듈로 나누어 자바 프로그램을 작성할 필요 없음

자바 JDK에 제공되는 모둘 파일들
    자바가 설치된 jmods 디렉터리에 모듈 파일 존재
        .imod 확장자를 가진 파일
        모듈은 수십개
        모듈 파일은 ZIP 포맷으로 압축된 파일
    모듈 파일에는 자바 API의 패키지와 클래스들이 들어 있음
    jomd 명령을 이용하여 모듈 파일에 들어 있는 패키지를 풀어 낼 수 있음

패키지 사용하기, import문
다른 패키지에 작성된 클래스 사용
    import를 이용하지 않는 경우
        소스에 클래스 이름이 완전 경로명 사용

필요한 클래스만 import
    소스 시각 부분에 클래스의 경로명 import
    import 패키지, 클래스
    소스에는 클래스 명만 명시하면 됨

패키지 전체를 import
    소스 시각 부분에 패키지의 경로명 import
    import 패키지.*
    import.java.util.*

가장 큰 목적
    자바 컴포넌트들을 필요에 따라 조립하여 사용하기 위함
    컴퓨터 시스템의 불필요한 부담 감소
        세밀한 모듈화를 통해 필요 없는 모듈이 로드되지 않게 함
        소형 IoT장치에도 자바 응용프로그램이 실행되고 성능을 유지하게함

Object 클래스
    특징
        모든 자바 클래스는 반드시 Object를 상속받도록 자동 컴파일
            모든 클래스의 수퍼 클래스
            모든 클래스가 상속받는 공통 메소드 포함

wrapper 클래스
자바의 기본타입을 클래스화한 8개 클래스를 통칭

용도
    객체만 사용할 수 있는 켈렉션 등에 기본 타입의 값을 사용하기 위해 wrapper 객체로 만들어서 사용

주요 메소드
가장 많이 사용하는 integer 클래스의 주요 메소드
    다른 wrapper 클래스와 메소ㅓ드는 이와 유사

2. 메소드 종류/설명
    toString(): 객체를 문자열로 반환합니다.
    hashCode(): 객체의 해시 코드를 반환합니다.
    equals(Object obj): 다른 객체와 동등한지 여부를 판단합니다.
    getClass(): 객체의 클래스 정보를 반환합니다.
    notify(): 대기 중인 하나의 스레드를 깨웁니다.
    notifyAll(): 대기 중인 모든 스레드를 깨웁니다.
    wait(): 스레드를 일시 정지시킵니다.

박싱과 언박싱
    박싱
        기본 타입의 값을 wrapper 객체로 변환하는 것

언박싱
    Wrapper 클래스의 객체에서 기본 타입의 값을 추출하는 것
    Wrapper 클래스는 기본 데이터 타입을 감싸는 클래스로, 예를 들어 Integer, Double, Boolean 등
    언박싱은 Wrapper 클래스의 객체에서 intValue(), doubleValue(), booleanValue() 등의 메소드를 사용하여 해당 기본 타입의 값을 추출

String 활용
String의 생성:
    문자열을 생성하고 문자열 리터럴을 저장하는 객체
    쌍따옴표(" ")로 감싸진 문자열 리터럴을 사용하여 String 객체를 생성가능
    문자열 연결 (Concatenation): '+' 연산자를 사용하여 문자열을 연결가능
    문자열 리터럴과 변수, 또는 문자열 변수와 문자열 변수를 연결가능

문자열의 길이 확인:
    length() 메소드를 사용하여 문자열의 길이를 확인
    공백을 포함하여 문자열의 전체 길이를 반환

문자열 비교:
    equals() 메소드를 사용하여 두 문자열이 동일한지 비교
    equalsIgnoreCase() 메소드는 대소문자를 무시하고 비교

부분 문자열 추출:
    substring() 메소드를 사용하여 문자열의 일부분을 추출
    시작 인덱스와 종료 인덱스를 지정하여 추출

문자열 검색:
    indexOf() 메소드를 사용하여 특정 문자 또는 문자열이 처음으로 등장하는 위치확인 가능
    lastIndexOf() 메소드는 문자열의 끝에서부터 검색을 수행

문자열 분할:
    split() 메소드를 사용하여 문자열을 특정 구분자를 기준으로 분할
    분할된 부분은 문자열 배열로 반환

문자열 치환:
    replace() 메소드를 사용하여 문자열 내의 특정 문자 또는 문자열을 다른 문자열로 대체
    대체될 문자열이 없으면 원래 문자열을 그대로 반환

## 4월 12일 강의

Static 맴버와 non-Static 맴버 특성 정리

Static 맴버의 생성
1. static맴버는 클래스당 하나만 생성
2. 객체들에 의해 공유됨

[ 5주차 강의 내용 참고 ]-----------------------------------------
static 맴버
1. static맴버 선언
2. 객체 생성과 non-static 맴버의 생성
    --> non-static 맴버는 객체가 생설될 때, 객체마다 생긴다

static 맴버 사용
1. 클래스 이름으로 접근이 가능
2. 객체의 맴버로 접근이 가느
3. non-static 맴버는 클래스 이름으로 접근 안됨
----------------------------------------------------------------

static 메소드의 제약 조건 1
1. tatic 메소드는 오직 static 맴버만 접근 가능
    <1>객체가 생성되지 않은 상황에서도 static메소드는 실행될 수 있기 때문에 non-static 멤버 활용불가
    <2>non-static 메소드는 static맴버 사용 가능

static 메소드의 제약 조건 2
1. static 메소드는 this 사용불가
    <1>static 메소드는 객체 없이도 사용 가능하므로, this 레퍼런스 사용할 수 없음

final 클래스와 메소드
1. final 클래스 -> 더 이상 클래스 상속 불가능
2. final 메소드 -> 더 이상 오버라이딩 불가능

final 필드
1. final필드, 상수 선언
    <1>상수를 선언할 때 사용
    <2>상수 필드는 선선 시에 초기 값을 지정하여아 한다
    <3>상수 필드는 생성중에 필드 값을 초기화 할 수 없다

상속 ( Inheritance )
1. 상속의 선언
    <1>extends 키워드로 선언
        --> 부모 클래스를 물려받아 확장한다는 의미
    <2>부모 클래스 --> 슈퍼 클래스 ( super class )
    <3>자식 클래스 --> 서브 클래스 ( sub class )

서브 클래스 객체의 모양
1. 슈퍼 클래스는 객체와 서브 클래스의 객체는 별개
2. 서브 클래스 객체는 슈퍼 클래스 맴버 포함

자바 상속의 특징
1. 클래스 다중 상속 불허
    <1> c++는 다중상속 클래스가 가능

슈퍼 클래스의 맴버에 대한 서브 클래스의 접근
1. 슈퍼 클래스의 private 맴버
    <1>서브 클래스에서 접근할 수 없음

2. 슈퍼 클래스의 디폴트 맴버
    <1>서브 클래스가 동일한 패키지에 있을 때, 접근 가능

3. 슈퍼 클래스의 public맴버
    <1>서브 클래스는 항상 접근 가능

4. 슈퍼 클래스의 protected 상속

protected 맴버
1. protected 맴버에 대한 접근 
    <1>같은 패키지의 모든 클래스에 접근 허용
    <2>상속되는 서브 클래스에게 허용

서브 클래스와 슈퍼 클래스의 생성자 선택 
1. 슈퍼 클래스와 서브클래스
    <1>각각 여러개의 생성자 작성가능

서브 클래스의 객체가 생성될 때
1. 슈퍼 클래스 생성자 1개와 서브 클래스 생성자 1개가 실행

서브 클래스의 생성자와 슈퍼 클래스의 생성자가 결정된는 방식
1. 개발자의 명시적 선택

[ 업캐스팅과 다운캐스팅 설명?? ]

업캐스팅의 개념
1. 업캐스팅은 하위 클래스를 상위 클래스 타입으로 변환하는 것. 상속 계층 구조에서 자주 사용되며, 다형성을 활용하여 다양한 객체를 동일한 인터페이스로 다룰 수 있게 컴파일 타임 다형성을 제공하여 유연하고 확장 가능한 코드를 작성하는 데 도움이 됨

업캐스팅
1. 서브 클래스의 레퍼런스를 슈퍼 클래스 레퍼런스에 대입
2. 슈퍼 클래스 레퍼런스로 서브 클래스 객체를 가르키게 되는 현상

다운캐스팅의 개념
1. 다운캐스팅은 상위 클래스 타입을 하위 클래스 타입으로 변환하는 것. 업캐스팅과 반대되며 명시적인 형 변환을 필요로, 주로 상위 클래스로 선언된 객체를 실제로는 하위 클래스로 사용해야 할 때 사용되며 타입 안전성을 보장하기 위해 주의해서 사용해야 함

다운캐스팅
1. 슈퍼 클래스 레퍼런스를 서브 클래스 레퍼런스에 대입
2. 업캐스팅된 것을 다시 월래대로 되돌리는 것
3. 반드시 명시적 타입 변환 지정

[ 업캐스팅 레퍼런스로 객체 구별?? ]
업캐스팅된 레퍼런스로는 객체의 실제 타입을 구분하기 어려움
    --> 슈퍼 클래스에서 여러 서브 클래스에 상속되기 때문.


Instanceof 연산자 사용
1. 레퍼런스가 가르키는 객체의 타입 식별

instanceof 연산자의 활용예제 ( by.코드 )
[ ex.code ]-----------------------------------------------------
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        System.out.println(myDog instanceof Animal); // true
        System.out.println(myDog instanceof Dog);    // true
        System.out.println(myDog instanceof Cat);    // false

        System.out.println(myCat instanceof Animal); // true
        System.out.println(myCat instanceof Dog);    // false
        System.out.println(myCat instanceof Cat);    // true
    }
}

설명: 이 예제는 instanceof 연산자를 사용하여 각 객체가 Animal 클래스, Dog 클래스 또는 Cat 클래스의 인스턴스인지를 확인한다.
[ ex.code ]------------------------------------------------------

메소드 오버라이딩의 개념
1. 메소드 오버라이딩은 상위 클래스의 메소드를 하위 클래스에서 덮어쓰는 것, 이를 통해 하위 클래스는 상위 클래스의 동작을 재정의할 수 있다. 이 개념은 다형성을 지원하며, 런타임 시에 객체의 실제 타입에 따라 적절한 메소드가 호출이 됨

오버라이딩의 목적, 다향성 실현
1. 오버라이딩 + 다향성
    <1>하나의 인터페이스에 서로 다른 구현
    <2>슈퍼 클래스의 메소드를 서브 클래세어서 각각 목적에 맞게 다르게 구현
    <3>사례

## 4월 5일 강의
자바의 예외 클래스
1. 배열의 범위를 벗어나 원소를 접근하는 예외 처리
<1> 자바에서 배열의 범위를 벗어나 원소를 접근하려고 할 때 발생하는 예외는 ArrayIndexOutOfBoundsException이고, 
이 예외는 배열의 유효하지 않은 인덱스에 접근하려고 할 때 발생 배열의 유효한 인덱스 범위는 0부터 배열의 길이 - 1까지이며, 이 범위를 벗어나는 인덱스에 접근하면 이 예외가 발생

캡슐화 : 객체를 캡슐로 싸서 내부를 볼 수 없게 하는 것
    객체의 가장 본질적인 특징
        외부의 접근으로부터 객체 보호

자바의 캡슐화
    클래스 : 객체 모양을 선언한 틀(캡슐화하는 틀)
    객체 : 생성된 실체
        클래스 내에 메소드와 필드 구현
상속
    상위 개체의 속성이 하위 개체에 물려짐
    하위 개체가 상위 개체의 속성을 모두 가지는 관계

자바 상속
    상위 클래스의 멤버를 하위 클래스가 물려받음
        상위 클래스 : 수퍼 클래스
        하위 클래스 : 서브 클래스, 수퍼 클래스 코드의 재사용, 새로운 특성 추가 가능

다형성
    같은 이름의 메소드가 클래스 혹은 객체에 따라 다르게 구현되는 것
    다형성 사례
        메소드 오버로딩 : 한 클래스 내에서 같은 이름이지만 다르게 작동하는 여러 메소드
        메소드 오버라이딩: 슈퍼 클래스의 메소드를 동일한 이름으로 서브 클래스마다 다르게 구현
소프트웨어의 생산성 향상
    컴퓨터 산업 발전에 따라 소프트웨어의 주기 단축
        소프트웨어를 빠른 속도로 생산할 필요성 증대
    객체 지향언어
        상속, 다형성, 객체, 캡슐화 등 소프트웨어 재사용을 위한 여러 장치 내장
        소프트웨어 재사용과 부분 수정 빠름
        소프트웨어를 다시 만다는 부담 대폭 줄임
        소프트웨어 생산성 향상

실세계의 대한 쉬운 모델링
    초기 프로그래밍
        수학 계산/동계 처리를 하는 등 처리 과정, 계산 절차 중요
    현대 프로그래밍
        컴퓨터가 산업 전반에 활용
        실세계에서 발생하는 일을 프로그래밍

    절차 지향 프로그래밍
        작업 순서를 표현하는 컴퓨터 명령 집함
        함수들의 집합으로 프로그램 작성
    객체 지향 프로그래밍 
        컴퓨터가 수행하는 작업을 객체들간의 상호 작용으로 표현
        클래스 혹은 객체들의 집합으로 프로그램 작성

클래스
    객체의 속성과 행위 선언
    객체의 설계도 혹은 틀

객체
    클래스의 틀로 찍어낸 실체
        프로그램 실행 중에 생성되는 실체
        메모리 공간을 갖는 구체적인 실체
        인스턴스라고도 부름

사례
    클래스: 소나타 자동차    객체: 출고된 실제 소나타 100대
    클래스: 벽시계           객체: 우리집 벽에 걸린 벽시계들
    클래스: 책상             객체: 우리가 사용중인 실제 책상들   

this
    객체 자신에 대한 레퍼런스
         컴파일러에 의해 자동관리, 개발자는 사용하기만 하면 됨
         this.멤버 형태로 멤버를 접근할 때 사용

자바의 객체 배열
    객체에 대한 레퍼런스 배열임
자바의 객체 배열 만들기 3단계
    1. 배열 레퍼런스 변수 선언
    2. 레퍼런스 배열 생성
    3. 배열의 각 원소 객체 생성

메소드
    메소드는 C/C++ 의 함수와 동일
    자바의모든 메소드는 반드시 클래스 안에 있어야함(캡슐화의 원칙)

객체 소멸
    new에 의해 할당 받은 객체와 배열 메모리를 자바 가상 기계로 되돌려 주는 행위
    소멸된 객체 공간은 가용 메모리에 포함
자바에서 사용자 임의로 객체 소멸 안 됨
    자바는 객체 소멸 연산자 없음
        객체 생성 연산자 : new
    객체 소멸은 자바 가상 기계의 고유한 역할
    자바 개발자에게는 매우 다행스러운 기능
        C/C++ 에서는 할당 받은 객체를 개발자가 프로그램 내에서 삭제해야 함
        C/C++ 의 프로그램 작성을 어렵게 만드는 요인
        자바에서는 사용하지 않는 객체나 배열을 돌려주는 코딩 책임으로부터 개발자 해방

가비지
    가리키는 레퍼런스가 하나도 없는 객체
        더 이상 접근할 수 없어 사용할 수 없게 된 메모리
가비지 컬렉션
    자바 가상 기계의 가비지 컬렉터가 자동으로 가비지 수집, 반환
    자바  가상 기계가 가비지 자동 회수
        가용 메모리 공간이 일정 이하로 부족해질 때
        가비지를 수거하여 가용 메모리 공간으로 확보
    가비지 컬렉터에 의해 자동수행

강제 가비지 컬렉션 강제수행
    System 또는 Runtime 객체의 gc() 메소드 호출
        이 코드는 자바 가상 기계에 강력한 가비지 컬렉션 요청
        그러나 자바 가상 기계가 가비지 컬렉션 시점을 전적으로 판단

패키지
    상호 관련 있는 클래스 파일(컴파일된.class)을 저장하여 관리하는 디렉터리
    자바 응용프로그램은 하나 이상의 패키지로 구성

자바의 접근 지정자
    4가지
        private, protected, public, 디폴트(접근지정자)
    접근 지정자의 목적
        클래스나 일부 멤버를 공개하여 다른 클래스에서 접근하도록 허용
        객체 지향 언어의 캡슐화 정책은 멤버를 보호하는 것
            접근 지정은 캡슐화에 묶인 보호를 일부 해체할 목적
        접근 지정자에 따른 클래스나 멤버의 공개 범위

클래스 접근지정
    다른 클래스에서 사용하도록 허용할 지 지정
    public 클래스
        다른 모든 클래스에게 접근 허용

디폴트 클래스(접근지정자 생략)
    package-private라고도 함
    같은 패키지의 클래스에만 접근 허용


## 3월 29일 강의
조건문
    Ex num1=100, num2=50;
       num1 < num2 ? T : F
            num2출력
            
    if문 / 가장 많이 사용하는 조건문
        else if, else

    switch문
        case, break, default

반복문
    for문 / 가장 많이 사용하는 반복문
    while문 / 조건식이 '참'인 동안 반복 실행
    do-while문 / 조건식이 '참'인 동안 반복 실행, 작업문은 한 번 반드시 실행

비트개념
    논리 연산: AND, OR, XOR, NOT 연산
        AND = &, OR = |, XOR = ^, NOT = ~a
    시프트 연산: 비트를 오른쪽이나 왼쪽으로 이동
    
배열(array)
    인덱스와 인덱스에 대응하는 데이터들로 이루어진 자료 구조(배열을 이용하면 한 번에 많은 메모리 공간 선언 가능)
    배열은 같은 타입의 데이터들이 순차적으로 저장되는 공간
    
    배열 인덱스
        0부터 시작
        인덱스는 배열의 시작 위치에서부터 데이터가 있는 상대 위치

        intArray = new int[5] / 배열 생성
        int intArray[] = (4,3,2,0,1) / 배열 초기화

    배열의 크기 length
        int size = intArray.length;

    2차원 배열
        int intArray[][]; / 2차원 배열 선언
        intArray = new int[2][5];  int intArray[] = new int[2][5]; // 배열 선언과 생성 동시

## 3월 22일 강의

ctrl + shift + p > java pro > java create java project > 파일 선택 > 파일 이름

자바 : .java > class
자바 사용 장점 : 한번 작성된 코드는 모든 플랫폼에 구애받지 않고(WORA, Write Once Run Anywhere) 돌아감
자바 사용 단점 : JVM(Java Virtual Machine)이 깔려있어야 함

개발하는사람은 JDK를 깔면 되고,
실행하는사람은 JRE를 깔면 된다

식별자란 : 클래스, 변수, 상수, 메소드 등에 붙이는 이름이다
대소문자 구별, Num과 num은 다른 변수이다.

변수 : 값을 임시 저장하기 위한 공간
리터럴 : 값(정수, 실수, 문자, 논리, 문자열 리터럴이 있다)

var키워드
    타입을 생략하고 변수 선언 가능
    컨파일러가 추론하여 변수 타입 결정
    
    변수 선언 시 초깃값이 주어지지 않으면 컴파일 오류 / 초기화 필수

타입변환
    한 타입의 값을 다른 타입의 값으로 변환

## 3월 15일 강의
내용 정리

이번에는 올리지마!!!
