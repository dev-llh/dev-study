import Link from "next/link";

export default function Page() {
  return (
    <div>
      <h1>Hello World!</h1>
      <a href="/sample1">jsx-1</a><br/>
      <a href="/sample2">jsx-2</a><br/>
      <a href="/sample3">props1</a><br/>
      <a href="/sample4">props2</a><br/>
      <a href="/event1">event1</a><br/>
      <a href="/event2">event2</a><br/>
      <a href="/state1">state1</a><br/>
      <Link href="/state2">state2</Link>
    </div>
  )
}