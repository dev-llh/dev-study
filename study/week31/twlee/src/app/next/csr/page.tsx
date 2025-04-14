"use client"

import { useEffect, useState } from "react"

type Data = {
  title?: string
  content?: string
}

export default function Page() {
const [data, setData] = useState<Data>({})

  useEffect(() => {
    fetch('/data.txt')
      .then((res) => res.json())
      .then(setData)
  }, [])

  return (
    <div>
      hello
      <p>title: {data.title}</p>
      <p>content: {data.content}</p>
    </div>
  )
}