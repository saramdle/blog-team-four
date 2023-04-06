import Container from "@/app/components/Container";
import { Metadata } from "next";
import Image from "next/image";
import CommentInput from "./CommentInput";
import Comments from "./Comments";

async function getPost(id: string) {
  const res = await fetch(`http://localhost:4000/posts/${id}`, {
    next: { revalidate: 60 * 60 },
  });
  if (!res.ok) {
    throw new Error("Failed to fetch data");
  }
  return res.json();
}

export async function generateMetadata({
  params,
}: {
  params: {
    postId: string;
  };
}): Promise<Metadata> {
  const post = await getPost(params.postId);
  return { title: post.title };
}

export default async function Page({
  params,
}: {
  params: {
    postId: string;
  };
}) {
  const postId = params.postId;
  const post: Post = await getPost(postId);
  return (
    <Container>
      <h1>{post?.title}</h1>
      <Image
        alt={post?.title || "food image"}
        src={post?.imgUrl || ""}
        width={300}
        height={300}
      />
      <p
        dangerouslySetInnerHTML={{ __html: post?.contents }}
        className='prose max-w-none prose-p:m-0'
      />
      <p>{post?.author}</p>
      <p>{post?.createdAt}</p>
      <CommentInput postId={postId} />
      <Comments postId={postId} />
    </Container>
  );
}

export async function generateStaticParams() {
  const res = await fetch("http://localhost:4000/posts");
  if (!res.ok) {
    throw new Error("Failed to fetch all the posts");
  }
  const posts: Post[] = await res.json();
  return posts.map((post) => ({
    postId: post.id.toString(),
  }));
}
