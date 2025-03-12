export default function MyButton({
  text="hello default",
  style={ bgColor: "yellow", size: "50px"}
}: {
  text: string
  style: { bgColor: string, size: string }
}) {
  return (
    <button style={{ backgroundColor: style.bgColor, width: style.size }}>{text}</button>
  )
}